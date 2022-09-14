package net.bodz.lily.security.login;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import net.bodz.bas.crypto.trans.EpochTransient;
import net.bodz.bas.crypto.trans.IFlyingSupport;
import net.bodz.bas.crypto.trans.IFlyingTransient;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.site.json.IJsonResponse;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.sms.IShortMessageService;
import net.bodz.bas.sms.SmsCommitLog;
import net.bodz.bas.sms.SmsProviders;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.impl.UserSecretMask;
import net.bodz.lily.security.login.ILoginResolver.Result;
import net.bodz.lily.security.login.resolver.EmailPasswordLoginResolver;
import net.bodz.lily.security.login.resolver.PhoneCheckLoginResolver;
import net.bodz.lily.security.ops.UserOps_PhoneId;

public class LoginManager
        extends LoginTokenManager
        implements
            ILoginManager,
            IFlyingSupport {

    // long timeout = 3600_000;
    DataContext dataContext;

    ILoginResolverProvider resolverProvider;
    List<ILoginListener> loginListeners = new ArrayList<>();

    public LoginManager(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;

        resolverProvider = new IndexedLoginResolverProvider(dataContext);
        for (ILoginListener listener : ServiceLoader.load(ILoginListener.class)) {
            loginListeners.add(listener);
        }
    }

    boolean debug = true;
    int window = 1_000;
    int distance = 10 * 60; // 10 minutes
    int allowAhead = 3 * 60; // 3 minutes
    IFlyingTransient core = new EpochTransient(window).transform(tr.partialMd5(6, 10));
    LoginCrypto crypto = new LoginCrypto(core, distance, allowAhead);
    int timeout = crypto.getTimeout(60_000);

    @Override
    public LoginResult initiateLogin(IVariantMap<String> q) {
        LoginResult result = new LoginResult();
        String serverChallenge = core.snapshot();
        result.setServerChallenge(serverChallenge);

        // diag helper.
        if (debug) {
            Integer userId = q.getInt("uid");
            if (userId != null) {
                String password = getAnyPassword(userId);
                if (password != null) {
                    // encrypted client response
                    String ecr = crypto.passwordSign(password).snapshot();
                    result.setHeader("e_passwd", ecr);
                }
            }
            String phone = q.getString("phone");
            if (phone != null) {
                String ecr = crypto.shortVerificationCode(phone).snapshot();
                result.setHeader("e_cr", ecr);
            }
        }
        return result;
    }

    @Override
    public LoginResult login(IVariantMap<String> q) {
        for (ILoginResolver resolver : resolverProvider.getResolvers()) {
            Result rr = resolver.login(crypto.passwordSignChecker, q);
            if (rr != null) {
                LoginResult result = rr.toLoginResult(this);
                result.setHeader("resolverClass", resolver.getClass().getCanonicalName());
                return result;
            }
        }
        return new LoginResult().fail("No successful login.");
    }

    @Override
    public LoginResult loginByPhone(String phone, String sign) {
        PhoneCheckLoginResolver resolver = new PhoneCheckLoginResolver(dataContext);
        return resolver.login(crypto.shortVerificationCodeChecker, phone, sign).toLoginResult(this);
    }

    @Override
    public LoginResult loginByEmail(String email, String sign) {
        EmailPasswordLoginResolver resolver = new EmailPasswordLoginResolver(dataContext);
        return resolver.login(crypto.shortVerificationCodeChecker, email, sign).toLoginResult(this);
    }

    String getAnyPassword(int userId) {
        UserSecretMapper secretMapper = dataContext.getMapper(UserSecretMapper.class);
        List<UserSecret> secrets = secretMapper.filter(new UserSecretMask().userId(userId), SelectOptions.ALL);
        for (UserSecret secret : secrets) {
            String password = secret.getPassword();
            if (password != null)
                return password;
        }
        return null;
    }

    @Override
    public IJsonResponse logout() {
        JsonResult r = new JsonResult();
        LoginToken token = LoginToken.fromSession();
        if (token != null) {
            User user = token.getUser();
            for (ILoginListener listener : loginListeners)
                try {
                    listener.logout(user);
                } catch (Exception e) {
                    return r.fail(e, "Can't logged out %s: %s", user.toString(), listener + ": " + e.getMessage());
                }
            LoginToken.clearSession();
        }
        return r.succeed();
    }

    /**
     * @param usage
     *            The reason to verify.
     * @see ILoginManager#VERIFY
     */
    @Override
    public IJsonResponse verifyPhone(String phone, String usage) {
        JsonResult resp = new JsonResult();
        String shortCode = crypto.shortVerificationCode(phone).snapshot();
        if (debug)
            resp.setHeader("e_cr", shortCode);

        IShortMessageService sms = SmsProviders.getSms();
        SmsCommitLog log = new SmsCommitLog();
        try {
            sms.addSmsListener(log);
            // if (!debug)
            if (!sms.sendPrepared(phone, usage, shortCode, timeout))
                return resp.fail("sms isn't available.");
        } catch (Exception e) {
            return resp.fail(e, "send sms error: " + e.getMessage());
        } finally {
            sms.removeSmsListener(log);
            resp.setHeader("logs", JsonFn.union(log));
        }
        return resp.succeed();
    }

    @Override
    public IJsonResponse verifyEmail(String address, String usage) {
        JsonResult resp = new JsonResult();
        String sign = crypto.sign(address).snapshot();
        resp.setHeader("sign", sign);
        throw new NotImplementedException("mail service isn't supported.");
    }

    @Override
    public LoginResult registerByPhone(String phone, String ecr, String password) {
        LoginResult result = new LoginResult();
        if (phone == null)
            return result.fail("phone isn't specified.");
        if (ecr == null)
            return result.fail("ecr isn't specified.");
        if (password == null)
            return result.fail("password isn't specified.");

        if (!crypto.checkShortVerificationCode(phone, ecr, result).exists())
            return result.fail("Invalid code.");

        UserOps_PhoneId ops = new UserOps_PhoneId(dataContext);
        return ops.register(this, phone, password);
    }

    @Override
    public LoginResult registerByEmail(String email, String ecr, String password) {
        LoginResult result = new LoginResult();
        if (email == null)
            return result.fail("email isn't specified.");
        if (ecr == null)
            return result.fail("ecr isn't specified.");
        if (password == null)
            return result.fail("password isn't specified.");

        if (!crypto.checkShortVerificationCode(email, ecr, result).exists())
            return result.fail("Invalid code.");

        return result.fail("Not implemented.");
    }

    @Override
    public LoginResult resetPasswordByPhone(String phone, String ecr, String password) {
        LoginResult result = new LoginResult();
        if (phone == null)
            return result.fail("phone isn't specified.");
        if (ecr == null)
            return result.fail("ecr isn't specified.");
        if (password == null)
            return result.fail("password isn't specified.");

        if (!crypto.checkShortVerificationCode(phone, ecr, result).exists())
            return result.fail("Invalid code.");

        UserOps_PhoneId ops = new UserOps_PhoneId(dataContext);
        return ops.resetPassword(this, phone, password);
    }

    @Override
    public LoginResult resetPasswordByEmail(String email, String ecr, String password) {
        LoginResult result = new LoginResult();
        if (email == null)
            return result.fail("email isn't specified.");
        if (ecr == null)
            return result.fail("ecr isn't specified.");
        if (password == null)
            return result.fail("password isn't specified.");

        if (!crypto.checkShortVerificationCode(email, ecr, result).exists())
            return result.fail("Invalid code.");

        return result.fail("Not implemented.");
    }

}

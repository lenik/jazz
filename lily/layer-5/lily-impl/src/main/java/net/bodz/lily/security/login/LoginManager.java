package net.bodz.lily.security.login;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import org.apache.commons.codec.digest.DigestUtils;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.JsonResponse;
import net.bodz.bas.sms.IShortMessageService;
import net.bodz.bas.sms.SmsProviders;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.impl.UserSecretMask;
import net.bodz.lily.security.login.ILoginResolver.Result;
import net.bodz.lily.security.login.key.FlyingSignatureChecker;
import net.bodz.lily.security.login.key.IFlyingCode;
import net.bodz.lily.security.login.resolver.EmailPasswordLoginResolver;
import net.bodz.lily.security.login.resolver.PhoneCheckLoginResolver;

public class LoginManager
        extends LoginTokenManager
        implements ILoginManager, IPathDispatchable {

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

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;

        switch (token) {
        case "init":
            target = initiateLogin(q);
            break;

        case "login":
            target = login(q);
            break;

        case "exit":
        case "quit":
        case "logout":
            target = logout();
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        return null;
    }

    boolean debug = true;
    int window = 1_000;
    int distance = 10 * 60; // 10 minutes
    FlyingSignatureChecker signChecker = new FlyingSignatureChecker(window, distance);

    @Override
    public LoginResult initiateLogin(IVariantMap<String> q) {
        LoginResult result = new LoginResult();
        String serverChallenge = signChecker.getSalt();
        result.setServerChallenge(serverChallenge);

        // diag helper.
        if (debug) {
            Integer userId = q.getInt("uid");
            if (userId != null) {
                String password = getAnyPassword(userId);
                if (password != null) {
                    String ecr = DigestUtils.shaHex(serverChallenge + password + serverChallenge);
                    result.set("e_passwd", ecr);
                }
            }
            String phone = q.getString("phone");
            if (phone != null) {
                String ecr = DigestUtils.shaHex(serverChallenge + phone + serverChallenge);
                result.set("e_cr", ecr);
            }
        }
        return result;
    }

    @Override
    public LoginResult login(IVariantMap<String> q) {
        for (ILoginResolver resolver : resolverProvider.getResolvers()) {
            Result rr = resolver.login(signChecker, q);
            if (rr == null)
                continue;
            return rr.toLoginResult(this);
        }
        return new LoginResult().fail("No successful login.");
    }

    @Override
    public LoginResult loginByPhone(String phone, String sign)
            throws LoginException {
        PhoneCheckLoginResolver resolver = new PhoneCheckLoginResolver(dataContext);
        return resolver.login(signChecker, phone, sign).toLoginResult(this);
    }

    @Override
    public LoginResult loginByEmail(String email, String sign)
            throws LoginException {
        EmailPasswordLoginResolver resolver = new EmailPasswordLoginResolver(dataContext);
        return resolver.login(signChecker, email, sign).toLoginResult(this);
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
    public JsonResponse logout() {
        JsonResponse r = new JsonResponse();
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

    @Override
    public void verifyPhone(String phone, String usage)
            throws LoginException {
        IShortMessageService sms = SmsProviders.getSms();
        IFlyingCode flyingCode = signChecker.getFlyingCode(phone);
        String code = flyingCode.getCodeForNow();
        try {
            sms.sendPrepared(phone, VERIFY, code);
        } catch (Exception e) {
            throw new LoginException("sms error: " + e.getMessage(), e);
        }
    }

    @Override
    public void verifyEmail(String address, String usage)
            throws LoginException {
        throw new NotImplementedException("mail service isn't supported.");
    }

    @Override
    public LoginResult registerByPhone(String phone, String code)
            throws LoginException {
        LoginResult result = new LoginResult();
        if (!signChecker.checkSignature(phone, code))
            return result.fail("Invalid code.");

        // 1. check if phone is in use

        // 2. auto create a user (random name)
        // 3. bind user with phone (other-id)
        // 4. auto login
        return result.succeed();
    }

    @Override
    public LoginResult registerByEmail(String email, String code)
            throws LoginException {
        LoginResult result = new LoginResult();
        if (!signChecker.checkSignature(email, code))
            return result.fail("Invalid code.");
        return result.succeed();
    }

    @Override
    public LoginResult resetPasswordByPhone(String phone, String code, String password)
            throws LoginException {
        LoginResult result = new LoginResult();
        if (!signChecker.checkSignature(phone, code))
            return result.fail("Invalid code.");
        return result.succeed();
    }

    @Override
    public LoginResult resetPasswordByEmail(String email, String code, String password)
            throws LoginException {
        LoginResult result = new LoginResult();
        if (!signChecker.checkSignature(email, code))
            return result.fail("Invalid code.");
        return result.succeed();
    }

}

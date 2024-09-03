package net.bodz.lily.security.auth.pam;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.ISignatureChecker;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserSecret;
import net.bodz.lily.schema.account.dao.UserManager;
import net.bodz.lily.schema.account.dao.UserOtherIdMapper;
import net.bodz.lily.schema.account.dao.UserSecretCriteriaBuilder;
import net.bodz.lily.schema.account.dao.UserSecretMapper;
import net.bodz.lily.security.auth.AuthContext;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.AuthException;
import net.bodz.lily.security.auth.FlyingCodes;
import net.bodz.lily.security.login.LoginResult;

public class PhonePasswordPam
        extends DatabasedPam {

    public static final String NAME = "phone-alt";

    public static final String K_PHONE = "phone";

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public PhonePasswordPam(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public AuthData authenticate(AuthContext authContext) {
        IVariantMap<String> q = authContext.getParameters();

        String phone = q.getString(K_PHONE);
        if (phone == null)
            return null;

        String sign = q.getString(K_ENC_PASSWD);
        if (sign == null)
            return null;

        return login(authContext, phone, sign);
    }

    public AuthData login(AuthContext authContext, String phone, String sign) {
        List<User> users = userMapper.selectByPhoneNumber(phone);
        switch (users.size()) {
        case 0:
            throw new IllegalArgumentException(String.format(//
                    "Not registered phone number: %s", phone));
        case 1:
            break;
        default:
            throw new IllegalArgumentException(String.format(//
                    "Multiple users using the same phone number %s.", phone));
        }

        User matchedUser = users.get(0);
        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretCriteriaBuilder().phone.eq(phone).get());
        if (userSecrets.isEmpty())
            throw new IllegalArgumentException(String.format(//
                    "User %s has no secret.", phone));

        ISignatureChecker checker = FlyingCodes.smsAuth.signChecker;

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            FlyingIndex fi = checker.checkSignature(passwd, sign);
            if (fi.exists()) {
                return AuthData.complete(this, matchedUser).flyingIndex(fi);
            }
        }

        throw new IllegalArgumentException(String.format(//
                "Incorrect password for user %s.", phone));
    }

    public AuthData registerByPhone(String phone, String ecr, String password)
            throws AuthException {
        LoginResult result = new LoginResult();
        if (phone == null)
            throw new IllegalArgumentException("phone isn't specified.");
        if (ecr == null)
            throw new IllegalArgumentException("ecr isn't specified.");
        if (password == null)
            throw new IllegalArgumentException("password isn't specified.");

        if (! FlyingCodes.smsAuth.checkSign(phone, ecr).applyOn(result).exists())
            throw new AuthException("Invalid code.");

//        UserManager ops = new UserManager(getDataContext());
//        return handleRegisterResult(ops.register(this, phone, password));
        return null;
    }

    public AuthData resetPasswordByPhone(String phone, String ecr, String password)
            throws AuthException {
        LoginResult result = new LoginResult();
        if (phone == null)
            throw new IllegalArgumentException("phone isn't specified.");
        if (ecr == null)
            throw new IllegalArgumentException("ecr isn't specified.");
        if (password == null)
            throw new IllegalArgumentException("password isn't specified.");

        if (! FlyingCodes.smsAuth.checkSign(phone, ecr).applyOn(result).exists())
            throw new AuthException("Invalid code.");

        UserManager ops = new UserManager(dataContext);
//        return ops.resetPassword(this, phone, password);
        return null;
    }

}

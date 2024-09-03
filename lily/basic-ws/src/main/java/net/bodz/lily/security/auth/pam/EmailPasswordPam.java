package net.bodz.lily.security.auth.pam;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.ISignatureChecker;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserSecret;
import net.bodz.lily.schema.account.dao.UserOtherIdMapper;
import net.bodz.lily.schema.account.dao.UserSecretCriteriaBuilder;
import net.bodz.lily.schema.account.dao.UserSecretMapper;
import net.bodz.lily.security.auth.AuthContext;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.AuthException;
import net.bodz.lily.security.auth.FlyingCodes;
import net.bodz.lily.security.login.LoginResult;

public class EmailPasswordPam
        extends DatabasedPam {

    public static final String NAME = "email";

    public static final String K_EMAIL = "email";

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public EmailPasswordPam(DataContext dataContext) {
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
        String email = q.getString(K_EMAIL);
        if (email == null)
            return null;

        String sign = q.getString(K_ENC_PASSWD);
        if (sign == null)
            return null;

        return authenticate(authContext, email, sign);
    }

    /**
     * @return Non-<code>null</code> result.
     */
    public AuthData authenticate(AuthContext authContext, String email, String sign) {
        List<User> users = userMapper.selectByPhoneNumber(email);
        switch (users.size()) {
        case 0:
            throw new IllegalArgumentException(String.format(//
                    "Not registered email: %s", email));
        case 1:
            break;
        default:

            throw new IllegalArgumentException(String.format(//
                    "Multiple users using the same email %s.", email));
        }

        User matchedUser = users.get(0);
        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretCriteriaBuilder().email.eq(email).get(), SelectOptions.TOP10);
        if (userSecrets.isEmpty())
            throw new IllegalArgumentException(String.format(//
                    "User %s has no secret.", email));

        ISignatureChecker checker = FlyingCodes.emailAuth.signChecker;

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            FlyingIndex fi = checker.checkSignature(passwd, sign);
            if (fi.exists()) {
                return AuthData.complete(this, matchedUser).flyingIndex(fi);
            }
        }

        throw new IllegalArgumentException(String.format(//
                "Incorrect password for user %s.", email));
    }

    public AuthData registerByEmail(String email, String ecr, String password)
            throws AuthException {
        LoginResult result = new LoginResult();
        if (email == null)
            throw new IllegalArgumentException("email isn't specified.");
        if (ecr == null)
            throw new IllegalArgumentException("ecr isn't specified.");
        if (password == null)
            throw new IllegalArgumentException("password isn't specified.");

        if (! FlyingCodes.emailAuth.checkSign(email, ecr).applyOn(result).exists())
            throw new AuthException("Invalid code.");

        throw new NotImplementedException();
    }

    public AuthData resetPasswordByEmail(String email, String ecr, String password)
            throws AuthException {
        LoginResult result = new LoginResult();
        if (email == null)
            throw new IllegalArgumentException("email isn't specified.");
        if (ecr == null)
            throw new IllegalArgumentException("ecr isn't specified.");
        if (password == null)
            throw new IllegalArgumentException("password isn't specified.");

        if (! FlyingCodes.emailAuth.checkSign(email, ecr).applyOn(result).exists())
            throw new AuthException("Invalid code.");

        throw new NotImplementedException();
    }

}

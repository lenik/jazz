package net.bodz.lily.security.auth.pam;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.ISignatureChecker;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserSecret;
import net.bodz.lily.schema.account.dao.UserOtherIdMapper;
import net.bodz.lily.schema.account.dao.UserSecretCriteriaBuilder;
import net.bodz.lily.schema.account.dao.UserSecretMapper;
import net.bodz.lily.security.auth.AuthContext;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.AuthModuleState;
import net.bodz.lily.security.auth.FlyingCodes;
import net.bodz.lily.security.auth.IAuthModuleState;

public class UserPasswordPam
        extends DatabasedPam {

    public static final String NAME = "passwd";

    public static final String K_USER_ID = "uid";
    public static final String K_USER_NAME = "user";

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    ISignatureChecker checker = FlyingCodes.passwordAuth.signChecker;

    public UserPasswordPam(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public IAuthModuleState getState(AuthContext authContext) {
        IVariantMap<String> q = authContext.getParameters();

        String sign = q.getString(K_ENC_PASSWD);
        if (sign == null)
            return AuthModuleState.disabled();

        return AuthModuleState.enabled(sign);
    }

    @Override
    public AuthData authenticate(AuthContext authContext) {
        IVariantMap<String> q = authContext.getParameters();

        String sign = q.getString(K_ENC_PASSWD);
        if (sign == null)
            return null;

        User user;

        Integer userId = q.getInt(K_USER_ID);
        if (userId != null) {
            user = userMapper.select(userId);
            if (user == null)
                throw new IllegalArgumentException(String.format(//
                        "Invalid user id: %s", userId));
        } else {
            String userName = q.getString(K_USER_NAME);
            if (userName == null)
                return null;
            user = userMapper.selectByName(userName);
            if (user == null)
                throw new IllegalArgumentException(String.format(//
                        "Invalid user name: %s", userName));
        }

        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretCriteriaBuilder().userId.eq(user.getId()).get());
        if (userSecrets.isEmpty())
            throw new IllegalArgumentException(String.format(//
                    "User %s has no secret.", user));

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            FlyingIndex fIndex = checker.checkSignature(passwd, sign);
            if (fIndex.exists()) {
                return AuthData.complete(this, sign, user).flyingIndex(fIndex);
            }
        }

        throw new IllegalArgumentException(String.format(//
                "Incorrect password for user %s.", user));
    }

}

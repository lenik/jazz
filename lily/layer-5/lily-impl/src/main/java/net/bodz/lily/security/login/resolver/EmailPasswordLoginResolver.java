package net.bodz.lily.security.login.resolver;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserOtherIdMapper;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.impl.UserSecretMask;
import net.bodz.lily.security.login.DataBackedLoginResolver;
import net.bodz.lily.security.login.key.ISignatureChecker;

public class EmailPasswordLoginResolver
        extends DataBackedLoginResolver
        implements ILoginConsts {

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public EmailPasswordLoginResolver(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public Result login(ISignatureChecker checker, IVariantMap<String> q) {
        String email = q.getString("email");
        if (email == null)
            email = q.getString("e-mail");
        if (email == null)
            return null;

        String sign = q.getString(K_PASSWD);
        if (sign == null)
            return null;

        return login(checker, email, sign);
    }

    /**
     * @return Non-<code>null</code> result.
     */
    public Result login(ISignatureChecker checker, String email, String sign) {
        List<User> users = userMapper.selectByPhoneNumber(email);
        switch (users.size()) {
        case 0:
            return failed("Not registered email: %s", email);
        case 1:
            break;
        default:
            return failed("Multiple users using the same email %s.", email);
        }

        User matchedUser = users.get(0);
        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretMask().email(email), SelectOptions.ALL);
        if (userSecrets.isEmpty())
            return failed("User %s has no secret.", email);

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            if (checker.checkSignature(passwd, sign)) {
                return new Result(matchedUser);
            }
        }

        return failed("Incorrect password for user %s.", email);
    }

}

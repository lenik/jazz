package net.bodz.lily.security.login;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.impl.UserOtherIdMapper;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.impl.UserSecretMask;
import net.bodz.lily.security.login.key.ISignatureChecker;

public class UserNameLoginResolver
        extends DataBackedLoginResolver {

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public UserNameLoginResolver(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public Result login(ISignatureChecker checker, IVariantMap<String> q) {
        String userName = q.getString("user");
        if (userName == null)
            userName = q.getString("username");
        if (userName == null)
            return null;

        String passwordCr = q.getString("cr");
        if (passwordCr == null)
            return failed("Password isn't specified.");

        User namedUser = userMapper.selectByName(userName);
        if (namedUser == null)
            return failed("No such user: %s", userName);

        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretMask().userName(userName), SelectOptions.ALL);
        if (userSecrets.isEmpty())
            return failed("User %s has no secret.", userName);

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            if (checker.checkSignature(passwd, passwordCr)) {
                return new Result(namedUser);
            }
        }

        return failed("Incorrect password for user %s.", userName);
    }

}

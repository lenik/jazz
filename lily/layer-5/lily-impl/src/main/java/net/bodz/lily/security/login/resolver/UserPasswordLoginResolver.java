package net.bodz.lily.security.login.resolver;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
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

public class UserPasswordLoginResolver
        extends DataBackedLoginResolver
        implements ILoginConsts {

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public UserPasswordLoginResolver(DataContext dataContext) {
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

        String sign = q.getString(K_PASSWD);
        if (sign == null)
            return null;

        User namedUser = userMapper.selectByName(userName);
        if (namedUser == null)
            return failed("No such user: %s", userName);

        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretMask().userName(userName), SelectOptions.ALL);
        if (userSecrets.isEmpty())
            return failed("User %s has no secret.", userName);

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            FlyingIndex fi = checker.checkSignature(passwd, sign);
            if (fi.exists()) {
                Result result = new Result(namedUser);
                result.setHeader("fi", fi);
                return result;
            }
        }

        return failed("Incorrect password for user %s.", userName);
    }

}

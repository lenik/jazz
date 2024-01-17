package net.bodz.lily.security.login.resolver;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ibatis.sql.SelectOptions;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSecret;
import net.bodz.lily.security.dao.UserOtherIdMapper;
import net.bodz.lily.security.dao.UserSecretCriteriaBuilder;
import net.bodz.lily.security.dao.UserSecretMapper;
import net.bodz.lily.security.login.DataBackedLoginResolver;
import net.bodz.lily.security.login.ISignatureChecker;

public class UserPasswordLoginResolver
        extends DataBackedLoginResolver
        implements
            ILoginConsts {

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public UserPasswordLoginResolver(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public Result login(ISignatureChecker checker, IVariantMap<String> q) {
        String sign = q.getString(K_PASSWD);
        if (sign == null)
            return null;

        User user;

        Integer userId = q.getInt("uid");
        if (userId != null) {
            user = userMapper.select(userId);
            if (user == null)
                return failed("Invalid user id: %s", userId);
        } else {
            String userName = q.getString("user");
            if (userName == null)
                userName = q.getString("uname");
            if (userName == null)
                return null;
            user = userMapper.selectByName(userName);
            if (user == null)
                return failed("Invalid user name: %s", userName);
        }

        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretCriteriaBuilder().userId(user.getId()), SelectOptions.ALL);
        if (userSecrets.isEmpty())
            return failed("User %s has no secret.", user);

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            FlyingIndex fi = checker.checkSignature(passwd, sign);
            if (fi.exists()) {
                Result result = new Result(user);
                result.setHeader("fi", fi);
                return result;
            }
        }

        return failed("Incorrect password for user %s.", user);
    }

}

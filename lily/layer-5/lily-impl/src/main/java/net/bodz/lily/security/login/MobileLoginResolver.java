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

public class MobileLoginResolver
        extends DataBackedLoginResolver {

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public MobileLoginResolver(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public Result login(ISignatureChecker checker, IVariantMap<String> q) {
        String mobile = q.getString("mobile");
        if (mobile == null)
            mobile = q.getString("tel");
        if (mobile == null)
            return null;

        String passwordCr = q.getString("cr");
        if (passwordCr == null) {
            return failed("Password isn't specified.");
        }

        List<User> users = userMapper.selectByMobile(mobile);
        switch (users.size()) {
        case 0:
            return failed("Not registered mobile number: %s", mobile);
        case 1:
            break;
        default:
            return failed("Multiple users using the same mobile number %s.", mobile);
        }

        User matchedUser = users.get(0);
        List<UserSecret> userSecrets = userSecretMapper.filter(//
                new UserSecretMask().userName(mobile), SelectOptions.ALL);
        if (userSecrets.isEmpty())
            return failed("User %s has no secret.", mobile);

        for (UserSecret userSecret : userSecrets) {
            String passwd = userSecret.getPassword();
            if (checker.checkSignature(passwd, passwordCr)) {
                return new Result(matchedUser);
            }
        }

        return failed("Incorrect password for user %s.", mobile);
    }

}

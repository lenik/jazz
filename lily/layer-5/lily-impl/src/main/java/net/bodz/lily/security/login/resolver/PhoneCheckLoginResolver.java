package net.bodz.lily.security.login.resolver;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserOtherIdMapper;
import net.bodz.lily.security.impl.UserSecretMapper;
import net.bodz.lily.security.login.DataBackedLoginResolver;
import net.bodz.lily.security.login.key.ISignatureChecker;

public class PhoneCheckLoginResolver
        extends DataBackedLoginResolver {

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public PhoneCheckLoginResolver(DataContext dataContext) {
        super(dataContext);
        this.userSecretMapper = dataContext.getMapper(UserSecretMapper.class);
        this.userOtherIdMapper = dataContext.getMapper(UserOtherIdMapper.class);
    }

    @Override
    public Result login(ISignatureChecker checker, IVariantMap<String> q) {
        String mobile = q.getString("mobile");
        if (mobile == null)
            return null;

        String sign = q.getString("e_cr");
        if (sign == null)
            return failed("Password isn't specified.");

        return login(checker, mobile, sign);
    }

    /**
     * @return Non-<code>null</code> result.
     */
    public Result login(ISignatureChecker checker, String mobile, String sign) {
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
        if (checker.checkSignature(mobile, sign))
            return new Result(matchedUser);

        return failed("Incorrect verification code for mobile number %s.", mobile);
    }

}

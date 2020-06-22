package net.bodz.lily.security.login.resolver;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;
import net.bodz.lily.security.UserOtherIdTypes;
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
        String phone = q.getString("phone");
        if (phone == null)
            return null;

        String sign = q.getString("e_cr");
        if (sign == null)
            return null; // failed("Password isn't specified.");

        return login(checker, phone, sign);
    }

    /**
     * @return Non-<code>null</code> result.
     */
    public Result login(ISignatureChecker checker, String phone, String sign) {
        List<User> users = userMapper.selectByPhoneNumber(phone);
        users = userMapper.selectByOtherId(UserOtherIdTypes.MOBILE, phone);
        switch (users.size()) {
        case 0:
            return failed("Not registered phone number: %s", phone);
        case 1:
            break;
        default:
            return failed("Multiple users using the same phone number %s.", phone);
        }

        User matchedUser = users.get(0);
        FlyingIndex fi = checker.checkSignature(phone, sign);
        if (fi.exists()) {
            Result result = new Result(matchedUser);
            result.setHeader("fi", fi);
            return result;
        }
        return failed("Incorrect verification code for phone number %s.", phone);
    }
}

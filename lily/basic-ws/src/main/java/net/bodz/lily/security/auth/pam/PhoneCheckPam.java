package net.bodz.lily.security.auth.pam;

import java.util.List;

import net.bodz.bas.crypto.trans.FlyingIndex;
import net.bodz.bas.crypto.trans.ISignatureChecker;
import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.UserOtherIdTypes;
import net.bodz.lily.schema.account.dao.UserOtherIdMapper;
import net.bodz.lily.schema.account.dao.UserSecretMapper;
import net.bodz.lily.security.auth.AuthContext;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.AuthModuleState;
import net.bodz.lily.security.auth.FlyingCodes;
import net.bodz.lily.security.auth.IAuthModuleState;

public class PhoneCheckPam
        extends DatabasedPam {

    public static final String NAME = "phone";

    static final String K_PHONE = "phone";

    protected UserSecretMapper userSecretMapper;
    protected UserOtherIdMapper userOtherIdMapper;

    public PhoneCheckPam(DataContext dataContext) {
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

        String sign = q.getString(K_ENC_CLIENT_RESP);
        if (sign == null)
            return AuthModuleState.disabled();

        return AuthModuleState.enabled(sign);
    }

    @Override
    public AuthData authenticate(AuthContext authContext) {
        IVariantMap<String> q = authContext.getParameters();

        String phone = q.getString(K_PHONE);
        if (phone == null)
            return null;

        String sign = q.getString(K_ENC_CLIENT_RESP);
        if (sign == null)
            return null; // failed("Password isn't specified.");

        return authenticate(authContext, phone, sign);
    }

    /**
     * @return Non-<code>null</code> result.
     */
    public AuthData authenticate(AuthContext authContext, String phone, String sign) {
        List<User> users = userMapper.selectByPhoneNumber(phone);
        users = userMapper.selectByOtherId(UserOtherIdTypes.MOBILE, phone);
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

        ISignatureChecker checker = FlyingCodes.smsAuth.signChecker;

        User matchedUser = users.get(0);
        FlyingIndex fi = checker.checkSignature(phone, sign);
        if (fi.exists()) {
            return AuthData.complete(this, sign, matchedUser).flyingIndex(fi);
        }

        throw new IllegalArgumentException(String.format(//
                "Incorrect verification code for phone number %s.", phone));
    }
}

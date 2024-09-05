package net.bodz.lily.security.auth.pam;

import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.schema.account.UserSecret;
import net.bodz.lily.schema.account.dao.UserSecretCriteriaBuilder;
import net.bodz.lily.schema.account.dao.UserSecretMapper;
import net.bodz.lily.security.auth.AuthContext;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.AuthException;
import net.bodz.lily.security.auth.AuthModuleState;
import net.bodz.lily.security.auth.FlyingCodes;
import net.bodz.lily.security.auth.IAuthModule;
import net.bodz.lily.security.auth.IAuthModuleState;

public class AuthInitiator
        extends DatabasedPam {

    public static final String NAME = "init";

    static final String K_SERVER_CHALLENGE = "sc"; // "serverChallenge";
    static final String K_UID = "uid";

    public AuthInitiator(DataContext dataContext) {
        super(dataContext);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public IAuthModuleState getState(AuthContext context) {
        return AuthModuleState.PENDING;
    }

    @Override
    public AuthData authenticate(AuthContext context)
            throws AuthException {
        IVariantMap<String> q = context.getParameters();

        String clientResp = q.getString(IAuthModule.K_ENC_CLIENT_RESP);
        if (clientResp != null)
            return null;

        String serverChallenge = FlyingCodes.core.snapshot();
        // JsonResult result = new JsonResult();
        context.setHeader(K_SERVER_CHALLENGE, serverChallenge);

        // diag helper.
        if (FlyingCodes.debug) {
            Integer userId = q.getInt("uid");
            if (userId != null) {
                String password = getAnyPassword(userId);
                if (password != null) {
                    // encrypted client response
                    String expectedSign = FlyingCodes.passwordAuth.sign(password).snapshot();
                    context.setHeader(IAuthModule.K_ENC_CLIENT_RESP, expectedSign);
                }
            }

            String phone = q.getString(PhoneCheckPam.K_PHONE);
            if (phone != null) {
                String expectedSign = FlyingCodes.smsAuth.sign(phone).snapshot();
                context.setHeader(IAuthModule.K_ENC_CLIENT_RESP, expectedSign);
            }
        }
        return AuthData.pending(this);
    }

    String getAnyPassword(int userId) {
        UserSecretMapper secretMapper = getDataContext().getMapper(UserSecretMapper.class);
        List<UserSecret> secrets = secretMapper.filter(new UserSecretCriteriaBuilder().userId.eq(userId).get());
        for (UserSecret secret : secrets) {
            String password = secret.getPassword();
            if (password != null)
                return password;
        }
        return null;
    }

}

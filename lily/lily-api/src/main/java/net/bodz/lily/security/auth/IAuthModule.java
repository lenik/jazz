package net.bodz.lily.security.auth;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IAuthModule
        extends
            IPriority {

    String K_SIGN = "sign";
    String K_ENC_PASSWD = "e_passwd";
    String K_ENC_CLIENT_RESP = "e_cr";

    @Override
    default int getPriority() {
        return 0;
    }

    boolean isAuto();

    String getName();

    IAuthModuleState getState(AuthContext authContext);

    AuthData authenticate(AuthContext authContext)
            throws AuthException;

}

package net.bodz.lily.security.auth;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.lily.security.login.ILoginManager;

@IndexedType
public interface ILoginManagerProvider
        extends
            IPriority {

    @Override
    default int getPriority() {
        return 0;
    }

    ILoginManager getLoginManager();

}

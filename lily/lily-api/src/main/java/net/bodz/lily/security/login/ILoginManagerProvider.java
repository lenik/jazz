package net.bodz.lily.security.login;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

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

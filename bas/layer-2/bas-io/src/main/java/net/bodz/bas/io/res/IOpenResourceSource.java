package net.bodz.bas.io.res;

import net.bodz.bas.meta.decl.EventSource;

public interface IOpenResourceSource
        extends EventSource {

    void addOpenResourceListener(IOpenResourceListener listener);

    void removeOpenResourceListener(IOpenResourceListener listener);

}

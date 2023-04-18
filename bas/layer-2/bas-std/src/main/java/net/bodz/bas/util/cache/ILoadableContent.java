package net.bodz.bas.util.cache;

import net.bodz.bas.std.rfc.http.ICacheControl;

public interface ILoadableContent {

    default ICacheControl getCacheControl() {
        return null;
    }

    void loadContent()
            throws Exception;

    void invalidateCache();

}

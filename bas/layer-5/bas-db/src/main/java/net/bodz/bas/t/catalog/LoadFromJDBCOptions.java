package net.bodz.bas.t.catalog;

import net.bodz.bas.t.scope.COptions;

public class LoadFromJDBCOptions
        extends COptions {

    static final String K_LOAD_KEYS = "loadKeys";

    public LoadFromJDBCOptions() {
    }

    public boolean isLoadKeys() {
        return getBoolean(K_LOAD_KEYS, false);
    }

    public void setLoadKeys(boolean loadKeys) {
        put(K_LOAD_KEYS, loadKeys);
    }

}

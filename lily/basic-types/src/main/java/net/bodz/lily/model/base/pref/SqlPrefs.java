package net.bodz.lily.model.base.pref;

import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

public class SqlPrefs
        extends AbstractPreferences {

    String section;

    public SqlPrefs(AbstractPreferences parent, String name) {
        super(parent, name);
    }

    @Override
    protected void putSpi(String key, String value) {
    }

    @Override
    protected String getSpi(String key) {
        return null;
    }

    @Override
    protected void removeSpi(String key) {
    }

    @Override
    protected void removeNodeSpi()
            throws BackingStoreException {
    }

    @Override
    protected String[] keysSpi()
            throws BackingStoreException {
        return null;
    }

    @Override
    protected String[] childrenNamesSpi()
            throws BackingStoreException {
        return null;
    }

    @Override
    protected SqlPrefs childSpi(String name) {
        return null;
    }

    @Override
    protected void syncSpi()
            throws BackingStoreException {
        flushSpi();
    }

    @Override
    protected void flushSpi()
            throws BackingStoreException {
    }

}

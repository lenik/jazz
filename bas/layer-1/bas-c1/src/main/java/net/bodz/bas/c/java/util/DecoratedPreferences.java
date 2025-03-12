package net.bodz.bas.c.java.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.model.IWrapper;

public class DecoratedPreferences
        extends Preferences
        implements
            IWrapper<Preferences> {

    Preferences wrapped;

    public DecoratedPreferences(Preferences wrapped) {
        if (wrapped == null)
            throw new NullPointerException("wrapped");
        this.wrapped = wrapped;
    }

    @NotNull
    @Override
    public Preferences getWrapped() {
        return wrapped;
    }

    @Override
    public void put(String key, String value) {
        getWrapped().put(key, value);
    }

    @Override
    public String get(String key, String def) {
        return getWrapped().get(key, def);
    }

    @Override
    public void remove(String key) {
        getWrapped().remove(key);
    }

    @Override
    public void clear()
            throws BackingStoreException {
        getWrapped().clear();
    }

    @Override
    public void putInt(String key, int value) {
        getWrapped().putInt(key, value);
    }

    @Override
    public int getInt(String key, int def) {
        return getWrapped().getInt(key, def);
    }

    @Override
    public void putLong(String key, long value) {
        getWrapped().putLong(key, value);
    }

    @Override
    public long getLong(String key, long def) {
        return getWrapped().getLong(key, def);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        getWrapped().putBoolean(key, value);
    }

    @Override
    public boolean getBoolean(String key, boolean def) {
        return getWrapped().getBoolean(key, def);
    }

    @Override
    public void putFloat(String key, float value) {
        getWrapped().putFloat(key, value);
    }

    @Override
    public float getFloat(String key, float def) {
        return getWrapped().getFloat(key, def);
    }

    @Override
    public void putDouble(String key, double value) {
        getWrapped().putDouble(key, value);
    }

    @Override
    public double getDouble(String key, double def) {
        return getWrapped().getDouble(key, def);
    }

    @Override
    public void putByteArray(String key, byte[] value) {
        getWrapped().putByteArray(key, value);
    }

    @Override
    public byte[] getByteArray(String key, byte[] def) {
        return getWrapped().getByteArray(key, def);
    }

    @Override
    public String[] keys()
            throws BackingStoreException {
        return getWrapped().keys();
    }

    @Override
    public String[] childrenNames()
            throws BackingStoreException {
        return getWrapped().childrenNames();
    }

    @Override
    public Preferences parent() {
        return getWrapped().parent();
    }

    @Override
    public Preferences node(String pathName) {
        return getWrapped().node(pathName);
    }

    @Override
    public boolean nodeExists(String pathName)
            throws BackingStoreException {
        return getWrapped().nodeExists(pathName);
    }

    @Override
    public void removeNode()
            throws BackingStoreException {
        getWrapped().removeNode();
    }

    @Override
    public String name() {
        return getWrapped().name();
    }

    @Override
    public String absolutePath() {
        return getWrapped().absolutePath();
    }

    @Override
    public boolean isUserNode() {
        return getWrapped().isUserNode();
    }

    @Override
    public String toString() {
        return getWrapped().toString();
    }

    @Override
    public void flush()
            throws BackingStoreException {
        getWrapped().flush();
    }

    @Override
    public void sync()
            throws BackingStoreException {
        getWrapped().sync();
    }

    @Override
    public void addPreferenceChangeListener(PreferenceChangeListener pcl) {
        getWrapped().addPreferenceChangeListener(pcl);
    }

    @Override
    public void removePreferenceChangeListener(PreferenceChangeListener pcl) {
        getWrapped().removePreferenceChangeListener(pcl);
    }

    @Override
    public void addNodeChangeListener(NodeChangeListener ncl) {
        getWrapped().addNodeChangeListener(ncl);
    }

    @Override
    public void removeNodeChangeListener(NodeChangeListener ncl) {
        getWrapped().removeNodeChangeListener(ncl);
    }

    @Override
    public void exportNode(OutputStream os)
            throws IOException, BackingStoreException {
        getWrapped().exportNode(os);
    }

    @Override
    public void exportSubtree(OutputStream os)
            throws IOException, BackingStoreException {
        getWrapped().exportSubtree(os);
    }

}

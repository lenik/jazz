package net.bodz.bas.c.java.util;

import java.util.prefs.Preferences;

public interface IPreferencesForm {

    void readObject(IPreferencesEx preferences);

    void writeObject(IPreferencesEx preferences);

    default IPreferencesEx defaultPreferences() {
        String path = getClass().getName().replace('.', '/');
        Preferences prefs = Preferences.userRoot().node(path);
        return new PreferencesEx(prefs);
    }

    default void loadPreferences() {
        readObject(defaultPreferences());
    }

    default void savePreferences() {
        writeObject(defaultPreferences());
    }

}

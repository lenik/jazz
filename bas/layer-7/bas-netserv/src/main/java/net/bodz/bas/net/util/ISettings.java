package net.bodz.bas.net.util;

import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;

public interface ISettings {

    @NotNull
    Set<String> getSettingNames();

    default boolean isSettingDefined(@NotNull String name) {
        return getSettingNames().contains(name);
    }

    Object getSetting(@NotNull String name);

    boolean setSetting(@NotNull String name, Object value);

    default void copySettings(ISettings settings) {
        for (String name : settings.getSettingNames()) {
            Object value = settings.getSetting(name);
            setSetting(name, value);
        }
    }

}

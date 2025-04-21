package net.bodz.bas.net.serv;

import java.util.Set;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.t.variant.MutableVariant;

public interface ISettings {

    @NotNull
    Set<String> getSettingNames();

    Object getSetting(@NotNull String name);

    default boolean setSetting(@NotNull String name, Object value) {
        IVariant variant = MutableVariant.wrap(value);
        return setSettingVar(name, variant);
    }

    boolean setSettingVar(@NotNull String name, @NotNull IVariant var);

    default void copySettings(ISettings settings) {
        for (String name : settings.getSettingNames()) {
            Object value = settings.getSetting(name);
            setSetting(name, value);
        }
    }

}

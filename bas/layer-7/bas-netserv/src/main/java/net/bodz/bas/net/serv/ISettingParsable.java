package net.bodz.bas.net.serv;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.path.IBasicTokenQueue;

public interface ISettingParsable
        extends ISettings {

    Object parseSetting(@NotNull String name, @NotNull IBasicTokenQueue args)
            throws ParseException;

    default boolean setSetting(@NotNull String name, @NotNull IBasicTokenQueue args)
            throws ParseException {
        Object value = parseSetting(name, args);
        return setSetting(name, value);
    }

}

package net.bodz.bas.net.util;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.path.IBasicTokenQueue;
import net.bodz.bas.t.variant.IVariant;
import net.bodz.bas.t.variant.MutableVariant;

public interface ISettingParsable
        extends ISettings {

    default boolean parseSetting(@NotNull String name, @NotNull IBasicTokenQueue args)
            throws ParseException {
        if (args.available() == 0)
            throw new ParseException("expect argument");
        String arg = args.peek();
        MutableVariant var = MutableVariant.wrap(arg);
        return setSettingVar(name, var);
    }

    @Override
    default boolean setSetting(@NotNull String name, Object value) {
        MutableVariant var = MutableVariant.wrap(value);
        return setSettingVar(name, var);
    }

    boolean setSettingVar(@NotNull String name, @NotNull IVariant var);

}

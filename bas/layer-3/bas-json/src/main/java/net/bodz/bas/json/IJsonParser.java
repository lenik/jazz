package net.bodz.bas.json;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;

@FunctionalInterface
public interface IJsonParser<T> {

    T parse(@NotNull JsonVariant in)
            throws ParseException;

}

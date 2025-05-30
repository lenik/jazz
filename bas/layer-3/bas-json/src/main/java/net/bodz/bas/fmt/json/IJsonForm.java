package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.bean.Internal;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IJsonForm {

    /**
     * (Don't override this method.)
     *
     * @param json <code>null</code> for the null object.
     */
    default void jsonIn(JsonVariant in)
            throws ParseException {
        jsonIn(in, JsonFormOptions.DEFAULT);
    }

    /**
     * (Don't override this method.)
     *
     * @param o Non-<code>null</code> value.
     * @throws NullPointerException if <code>o</code> is null.
     */
    default void jsonIn(@NotNull JsonObject o)
            throws ParseException {
        jsonIn(o, JsonFormOptions.DEFAULT);
    }

    /**
     * @param json Non-<code>null</code> value.
     * @throws NullPointerException if <code>o</code> is null.
     */
    default void jsonIn(@NotNull JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        if (in.isNull()) {
            // jsonInNull ?
            return;
        }
        JsonObject jo = in.getObject();
        assert jo != null;
        jsonIn(jo, opts);
    }

    /**
     * @param json Non-<code>null</code> value.
     * @throws NullPointerException if <code>o</code> is null.
     */
    void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException;

    /**
     * @param out expects a key
     */
    default void jsonOut(IJsonOut out)
            throws IOException, FormatException {
        jsonOut(out, JsonFormOptions.DEFAULT);
    }

    /**
     * @param out expects a key
     */
    void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException;

    default boolean wantObjectContext() {
        return true;
    }

    default void jsonOutValue(IJsonOut out)
            throws IOException, FormatException {
        jsonOutValue(out, JsonFormOptions.DEFAULT);
    }

    default void jsonOutValue(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        if (wantObjectContext())
            out.object();
        jsonOut(out);
        if (wantObjectContext())
            out.endObject();
    }

    @Internal
    default boolean isJsonInByLoader() {
        return false;
    }

    @Internal
    default boolean isJsonOutByDumper() {
        return false;
    }

    class json
            extends JsonFn {
    }

}

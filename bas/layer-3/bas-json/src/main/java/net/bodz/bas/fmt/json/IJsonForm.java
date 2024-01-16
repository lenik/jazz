package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IJsonForm {

    /**
     * (Don't override this method.)
     *
     * @param json
     *            <code>null</code> for the null object.
     */
    default void jsonIn(JsonVariant in)
            throws ParseException {
        jsonIn(in, JsonFormOptions.DEFAULT);
    }

    /**
     * (Don't override this method.)
     *
     * @param json
     *            <code>null</code> for the null object.
     */
    default void jsonIn(JsonObject o)
            throws ParseException {
        jsonIn(o, JsonFormOptions.DEFAULT);
    }

    /**
     * @param json
     *            <code>null</code> for the null object.
     */
    default void jsonIn(JsonVariant in, JsonFormOptions opts)
            throws ParseException {
        jsonIn(in.getObject(), opts);
    }

    /**
     * @param json
     *            <code>null</code> for the null object.
     */
    void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException;

    /**
     * @param out
     *            expects a key
     */
    default void jsonOut(IJsonOut out)
            throws IOException, FormatException {
        jsonOut(out, JsonFormOptions.DEFAULT);
    }

    /**
     * @param out
     *            expects a key
     */
    void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException;

    /**
     * @param out
     *            expects a value
     */
    default void jsonOut(IJsonOut out, JsonFormOptions opts, boolean scalar)
            throws IOException, FormatException {
        if (scalar) {
            out.object();
            jsonOut(out, opts);
            out.endObject();
        } else {
            jsonOut(out, opts);
        }
    }

    default boolean isJsonInByLoader() {
        return false;
    }

    default boolean isJsonOutByDumper() {
        return false;
    }

    class json
            extends JsonFn {
    }

}

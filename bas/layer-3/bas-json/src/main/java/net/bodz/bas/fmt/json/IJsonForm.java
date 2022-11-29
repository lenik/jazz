package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IJsonForm {

    /**
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

    default void readObjectBoxed(Object obj, JsonFormOptions opts)
            throws ParseException {
        if (obj instanceof JsonObject) {
            JsonObject jo = (JsonObject) obj;
            jsonIn(jo, opts);
            return;
        }
        throw new UnsupportedOperationException("Can't read from non-object json value.");
    }

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

    class json
            extends JsonFn {
    }

}

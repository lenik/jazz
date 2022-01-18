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
    void readObject(JsonObject o)
            throws ParseException;

    /**
     * @param out
     *            expects a key
     */
    void writeObject(IJsonOut out)
            throws IOException, FormatException;

    default void readObjectBoxed(Object obj)
            throws ParseException {
        if (obj instanceof JsonObject) {
            JsonObject jo = (JsonObject) obj;
            readObject(jo);
            return;
        }
        throw new UnsupportedOperationException("Can't read from non-object json value.");
    }

    /**
     * @param out
     *            expects a value
     */
    default void writeObjectBoxed(IJsonOut out)
            throws IOException, FormatException {
        out.object();
        writeObject(out);
        out.endObject();
    }

    class json
            extends JsonFn {
    }

}

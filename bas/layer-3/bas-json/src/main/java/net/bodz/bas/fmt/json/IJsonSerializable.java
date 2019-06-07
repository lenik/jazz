package net.bodz.bas.fmt.json;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IJsonSerializable {

    /**
     * @param json
     *            <code>null</code> for the null object.
     */
    void readObject(JsonObject o)
            throws ParseException;

    void writeObject(IJsonOut out)
            throws IOException;

    class json
            extends JsonFn {
    }

}

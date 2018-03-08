package net.bodz.bas.fmt.json;

import java.io.IOException;

import org.json.JSONObject;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IJsonSerializable {

    /**
     * @param json
     *            <code>null</code> for the null object.
     */
    void readObject(JSONObject json)
            throws ParseException;

    void writeObject(IJsonOut out)
            throws IOException;

    class json
            extends JsonFn {
    }

}

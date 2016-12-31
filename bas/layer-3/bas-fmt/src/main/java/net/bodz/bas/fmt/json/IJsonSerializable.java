package net.bodz.bas.fmt.json;

import java.io.IOException;

import org.json.JSONObject;
import org.json.JSONWriter;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.source.SerializableForm;

@SerializableForm
public interface IJsonSerializable {

    void readObject(JSONObject json)
            throws ParseException;

    void writeObject(JSONWriter out)
            throws IOException;

    class json
            extends JsonFn {
    }

}

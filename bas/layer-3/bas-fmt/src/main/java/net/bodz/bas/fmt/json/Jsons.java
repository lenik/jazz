package net.bodz.bas.fmt.json;

import java.io.StringWriter;

import org.json.JSONObject;
import org.json.JSONWriter;

import net.bodz.bas.err.ParseException;

public class Jsons {

    public static String toJson(IJsonSerializable obj) {
        StringWriter buf = new StringWriter();
        JSONWriter writer = new JSONWriter(buf);
        obj.writeObject(writer);
        return buf.toString();
    }

    public static <T extends IJsonSerializable> T fromJson(T obj, String json)
            throws ParseException {
        JSONObject jsonObj = new JSONObject(json);
        obj.readObject(jsonObj);
        return obj;
    }

}

package net.bodz.bas.json;

import net.bodz.fork.org.json.JSONException;

public class JsonBuilder {

    JsonObjectBuilder job = JsonObjectBuilder.getInstance();
    JsonArrayBuilder jab = JsonArrayBuilder.getInstance();

    public Object parse(String source)
            throws JSONException {
        source = source.trim();
        if (source.isEmpty())
            throw new JSONException("empty source");

        switch (source.charAt(0)) {
        case '[':
            return jab.parse(source);
        case '{':
            return job.parse(source);
        default:
            return JsonObject.stringToValue(source);
        }
    }

    static JsonBuilder instance = new JsonBuilder();

    public static JsonBuilder getInstance() {
        return instance;
    }

}

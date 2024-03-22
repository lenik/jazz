package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class JsonWithRef {

    public static JsonObject encode(JsonObject o) {
        return new JsonWithRefEncoder().encode(o).getObject();
    }

    public static JsonArray encode(JsonArray o) {
        return new JsonWithRefEncoder().encode(o).getArray();
    }

    public static JsonVariant encode(JsonVariant jv) {
        return new JsonWithRefEncoder().encode(jv);
    }

    public static Object encode(Object o) {
        return new JsonWithRefEncoder().encode(o).value;
    }

    public static JsonObject decode(JsonObject o) {
        return new JsonWithRefDecoder().decodeObject(o);
    }

    public static JsonArray decode(JsonArray o) {
        return new JsonWithRefDecoder().decodeArray(o);
    }

    public static JsonVariant decode(JsonVariant jv) {
        return new JsonWithRefDecoder().decode(jv);
    }

    public static Object decode(Object o) {
        return new JsonWithRefDecoder().decode(o).value;
    }

}

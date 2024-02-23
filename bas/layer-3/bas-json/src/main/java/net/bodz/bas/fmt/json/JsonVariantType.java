package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public enum JsonVariantType {

    OBJECT,

    ARRAY,

    SCALAR,

    NULL,

    ;

    public static JsonVariantType getType(Object value) {
        if (value == null)
            return JsonVariantType.NULL;
        if (value instanceof JsonObject)
            return JsonVariantType.OBJECT;
        if (value instanceof JsonArray)
            return JsonVariantType.ARRAY;
        return JsonVariantType.SCALAR;
    }

}

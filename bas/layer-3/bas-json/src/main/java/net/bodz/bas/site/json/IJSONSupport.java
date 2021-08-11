package net.bodz.bas.site.json;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public interface IJSONSupport {

    class json {

        public static Object toVar(Object jsonObj) {
            if (jsonObj == null)
                return null;
            if (jsonObj == JsonObject.NULL)
                return null;
            if (jsonObj instanceof JsonArray) {
                JsonArray jsv = (JsonArray) jsonObj;
                return new JsonArrayList(jsv);
            }
            if (jsonObj instanceof JsonObject) {
                JsonObject jso = (JsonObject) jsonObj;
                return new JsonVarMap(jso);
            }
            return jsonObj;
        }

    }

}

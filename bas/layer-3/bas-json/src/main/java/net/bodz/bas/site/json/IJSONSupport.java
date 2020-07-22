package net.bodz.bas.site.json;

import net.bodz.json.JSONArray;
import net.bodz.json.JSONObject;

public interface IJSONSupport {

    class json {

        public static Object toVar(Object jsonObj) {
            if (jsonObj == null)
                return null;
            if (jsonObj == JSONObject.NULL)
                return null;
            if (jsonObj instanceof JSONArray) {
                JSONArray jsv = (JSONArray) jsonObj;
                return new JsonArrayList(jsv);
            }
            if (jsonObj instanceof JSONObject) {
                JSONObject jso = (JSONObject) jsonObj;
                return new JsonVarMap(jso);
            }
            return jsonObj;
        }

    }

}

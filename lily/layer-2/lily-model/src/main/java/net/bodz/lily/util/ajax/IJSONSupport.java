package net.bodz.lily.util.ajax;

import org.json.JSONArray;
import org.json.JSONObject;

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

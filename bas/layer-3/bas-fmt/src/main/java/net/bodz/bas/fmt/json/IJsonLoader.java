package net.bodz.bas.fmt.json;

import org.json.JSONObject;

public interface IJsonLoader {

    void load(Object obj, JSONObject node)
            throws Exception;

}

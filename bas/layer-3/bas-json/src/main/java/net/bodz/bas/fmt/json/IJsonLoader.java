package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonObject;

public interface IJsonLoader {

    void load(Object obj, JsonObject node)
            throws Exception;

}

package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonObject;

public interface IJsonLoader {

    JsonFormOptions getOptions();

    void setOptions(JsonFormOptions options);

    void load(Object obj, JsonObject node)
            throws Exception;

}

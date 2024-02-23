package net.bodz.lily.concrete;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;

public interface IHaveProperties {

    JsonVariant getProperties();

    void setProperties(JsonVariant properties);

    default JsonObject properties() {
        JsonVariant properties = getProperties();
        JsonObject jo;
        if (properties == null || ! properties.isObject()) {
            jo = new JsonObject();
            properties = JsonVariant.of(jo);
            setProperties(properties);
        } else {
            jo = properties.getObject();
        }
        return jo;
    }

}

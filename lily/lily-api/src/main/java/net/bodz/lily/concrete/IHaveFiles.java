package net.bodz.lily.concrete;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;

public interface IHaveFiles {

    JsonVariant getFiles();

    void setFiles(JsonVariant files);

    default JsonObject files() {
        JsonVariant files = getFiles();
        JsonObject jo;
        if (files == null || ! files.isObject()) {
            jo = new JsonObject();
            files = JsonVariant.of(jo);
            setFiles(files);
        } else {
            jo = files.getObject();
        }
        return jo;
    }

}

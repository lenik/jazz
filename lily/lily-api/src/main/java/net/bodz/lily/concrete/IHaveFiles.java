package net.bodz.lily.concrete;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;

public interface IHaveFiles {

    JsonVariant getFiles();

    void setFiles(JsonVariant files)
            throws ParseException;

    default JsonObject files() {
        synchronized (this) {
            JsonVariant files = getFiles();
            if (files == null) {
                JsonObject jo = new JsonObject();
                try {
                    setFiles(JsonVariant.of(jo));
                } catch (ParseException e) {
                    throw new UnexpectedException(e);
                }
                return jo;
            }
            if (files.isArray()) {
                JsonObject jo = new JsonObject();
                jo.put("data", files.getArray());
                try {
                    setFiles(JsonVariant.of(jo));
                } catch (ParseException e) {
                    throw new UnexpectedException(e);
                }
                return jo;
            }
            if (files.isObject())
                return files.getObject();
            throw new UnexpectedException();
        }
    }

}

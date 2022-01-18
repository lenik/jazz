package net.bodz.bas.json;

import java.util.ArrayList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.fork.org.json._JSONArray;

public class JsonArray
        extends _JSONArray {

    public JsonArray() {
        super();
    }

    public JsonArray(ArrayList<Object> list) {
        super(list);
    }

    public <T extends IJsonForm> T readInto(int index, T obj)
            throws ParseException {
        return readInto(index, obj, null);
    }

    public <T extends IJsonForm> T readInto(int index, T obj, T newObj)
            throws ParseException {
        if (index >= length())
            return obj;

        JsonObject node = null;
        Object _node = get(index);
        if (_node == null) // force set to null
            return null;
        if (_node instanceof JsonObject)
            node = (JsonObject) _node;

        if (node != null) {
            if (obj == null) {
                if (newObj == null) // don't auto-create
                    return null;
                obj = newObj;
            }
            obj.readObject(node);
        }

        return obj;
    }

}

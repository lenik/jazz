package net.bodz.bas.json;

import java.util.ArrayList;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.fork.org.json._JSONArray;

public class JsonArray
        extends _JSONArray {

    public JsonArray() {
        super();
    }

    public JsonArray(ArrayList<Object> list) {
        super(list);
    }

    public <T extends IJsonForm> T readInto(int index, T obj, JsonFormOptions opts)
            throws ParseException {
        return readInto(index, obj, null, opts);
    }

    public <T extends IJsonForm> T readInto(int index, T obj, T newObj, JsonFormOptions opts)
            throws ParseException {
        if (index >= length())
            return obj;

        Object _node = get(index);
        if (_node == null) // force set to null
            return null;

        JsonVariant node = null;
        node = JsonVariant.of(_node);

        if (node != null) {
            if (obj == null) {
                if (newObj == null) // don't auto-create
                    return null;
                obj = newObj;
            }
            obj.jsonIn(node, opts);
        }

        return obj;
    }

}

package net.bodz.bas.fmt.json;

import net.bodz.bas.err.FormatException;

public abstract class JsonStruct
        implements
            IJsonSerializable {

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj != this)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        JsonStruct o = (JsonStruct) obj;
        return jsonEquals(o);
    }

    public boolean jsonEquals(JsonStruct o) {
        try {
            String js1 = JsonFn.toJson(this);
            String js2 = JsonFn.toJson(o);
            if (js1.equals(js2))
                return false;
            return true;
        } catch (FormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int hashCode() {
        try {
            String json = JsonFn.toJson(this);
            return json.hashCode();
        } catch (FormatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        try {
            return JsonFn.toJson(this);
        } catch (FormatException e) {
            throw new RuntimeException(e);
        }
    }

}

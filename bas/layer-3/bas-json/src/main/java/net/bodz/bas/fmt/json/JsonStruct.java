package net.bodz.bas.fmt.json;

public abstract class JsonStruct
        implements IJsonSerializable {

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
        String js1 = JsonFn.toJson(this);
        String js2 = JsonFn.toJson(o);
        if (js1.equals(js2))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        String json = JsonFn.toJson(this);
        return json.hashCode();
    }

    @Override
    public String toString() {
        return JsonFn.toJson(this);
    }

}

package net.bodz.lily.t.struct;

import java.io.Serializable;

import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonFn;

public abstract class MixinStruct
        implements IJsonSerializable, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return JsonFn.toJson(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false;
        MixinStruct o = (MixinStruct) obj;
        String js1 = JsonFn.toJson(this);
        String js2 = JsonFn.toJson(o);
        return js1.equals(js2);
    }

    @Override
    public int hashCode() {
        String json = JsonFn.toJson(this);
        return json.hashCode();
    }

}

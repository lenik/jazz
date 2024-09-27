package net.bodz.bas.json;

import java.util.Map;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.typer.std.MutableTypedAttributes;

public class JsonObjects {

    public static <O> O accept(JsonObject jo, IJsonVisitor<O, ?> visitor) {
        return visitor.object(jo);
    }

    public static <A> A accept(JsonArray ja, IJsonVisitor<?, A> visitor) {
        return visitor.array(ja);
    }

    public static Object accept(JsonVariant jv, IJsonVisitor<?, ?> visitor) {
        switch (jv.getType()) {
        case ARRAY:
            return visitor.array(jv.getArray());
        case OBJECT:
            return visitor.object(jv.getObject());
        case SCALAR:
            return visitor.simpleValue(jv.getScalar());
        case NULL:
            return visitor._null();
        default:
            throw new UnexpectedException();
        }
    }

    public static Object accept(Object any, IJsonVisitor<?, ?> visitor) {
        if (any == null) {
            return visitor._null();
        }
        if (any instanceof JsonObject) {
            return visitor.object((JsonObject) any);
        }
        if (any instanceof JsonArray) {
            return visitor.array((JsonArray) any);
        }
        if (any instanceof JsonVariant) {
            return accept((JsonVariant) any, visitor);
        }
        return visitor.simpleValue(any);
    }

    public static MutableTypedAttributes parseAttributes(JsonObject o) {
        Map<String, Object> map = JsonObjects.accept(o, new SimpleConverter());
        MutableTypedAttributes a = new MutableTypedAttributes();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            a.setAttribute(key, value);
        }
        return a;
    }

    public static void main(String[] args)
            throws Exception {
        JsonVariant jv = JsonFn.parseAny("{ \"foo\": 123, \"bar\": { \"a\": 1 }, \"cat\": [ 1, 2, \"str\", 10 ] }");
        MutableTypedAttributes a = parseAttributes(jv.getObject());
        System.out.println(a);
    }

}

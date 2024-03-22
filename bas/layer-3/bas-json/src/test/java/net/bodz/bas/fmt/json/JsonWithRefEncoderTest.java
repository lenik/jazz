package net.bodz.bas.fmt.json;

import org.junit.Test;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class JsonWithRefEncoderTest {

    @Test
    public void test() {
        JsonObject node1 = new JsonObject();
        node1.put("id", 1);
        node1.put("self", node1);

        JsonObject node2 = new JsonObject();
        node2.put("id", 2);
        node2.put("self", node2);
        node2.put("parent", node1);

        JsonArray v = new JsonArray();
        v.put("n1: ");
        v.put(node1);
        v.put("n2: ");
        v.put(node2);
        v.put("self: ");
        v.put(v);
        v.put("end");
        node2.put("array", v);

        node1.put("child", node2);
        node1.put("array", v);

        JsonWithRefEncoder encoder = new JsonWithRefEncoder();
        JsonVariant jv = encoder.encode(node1);
        String json = jv.getObject().toString();
        System.out.println(json);
    }

}

package net.bodz.bas.fmt.json;

import net.bodz.bas.json.JsonObject;
import net.bodz.bas.repr.form.SortOrder;

public class JSONSortedObjectTest {

    public static void main(String[] args) {
        JsonObject a = mkTest();
        JsonObject b = mkTest();
        JsonObject c = mkTest();
        a.put("b", b);
        a.put("c", c);
        System.out.println(a.keySet());
        System.out.println();
    }

    static JsonObject mkTest() {
//        JsonObject o = new JsonObject();
        JsonObject o = new JsonObject(SortOrder.ASCENDING);
        for (int i = 0; i < 30; i++)
            o.put("a" + i, i);
        return o;
    }

}

package net.bodz.bas.fmt.json;

import org.json.JSONObject;

public class JSONSortedObjectTest {

    public static void main(String[] args) {
        JSONObject a = mkTest();
        JSONObject b = mkTest();
        JSONObject c = mkTest();
        a.put("b", b);
        a.put("c", c);
        System.out.println(a);
        System.out.println();
        System.out.println(new JSONSortedObject(a));
    }

    static JSONObject mkTest() {
        JSONObject o = new JSONObject();
        for (int i = 0; i < 10; i++)
            o.put("a" + i, i);
        return o;
    }

}

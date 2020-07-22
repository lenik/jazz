package net.bodz.bas.fmt.json;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.json.JSONObject;

public class JsonFnTest
        extends Assert {

    @Test
    public void test() {
    }

    public static void main(String[] args) {
        String json = "{ 'person': { 'age': 13, 'name': 'lenik', 'sex': true }, 'features': [ 'hot', 123, 'lazy', 'sexy' ] }";
        json = json.replace('\'', '"');
        JSONObject obj = new JSONObject(json);
        System.out.println(obj.toString(4));

        Map<String, Object> map = JsonFn.toMap(obj);
        System.out.println(map);
    }

}

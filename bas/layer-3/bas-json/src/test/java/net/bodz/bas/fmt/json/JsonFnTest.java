package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.json.JsonObjectBuilder;

public class JsonFnTest
        extends Assert {

    final String abcJson = "{\"a\":1, \"b\":{\"c\":2,\"d\":3}}";
    final String personFeatureJson;
    {
        String q = "{ 'person': { 'age': 13, 'name': 'lenik', 'sex': true }, 'features': [ 'hot', 123, 'lazy', 'sexy' ] }";
        personFeatureJson = q.replace(StringQuote.singleQuoteChar, StringQuote.doubleQuoteChar);
    }

    @Test
    public void testPretty()
            throws IOException {
        String pretty = JsonFn.pretty(abcJson, ".", "--indent", "3");
        assertEquals("{\n" //
                + "   \"a\": 1,\n" //
                + "   \"b\": {\n" //
                + "      \"c\": 2,\n" //
                + "      \"d\": 3\n" //
                + "   }\n" //
                + "}\n" //
                + "", pretty);
    }

    final JsonObject abc = JsonObjectBuilder.getInstance().parse(abcJson);
    final JsonObject personFeature = JsonObjectBuilder.getInstance().parse(personFeatureJson);

    @Test
    public void testIndent1() {
        String json = abc.toString(4);
        assertEquals("{\n" //
                + "    \"a\": 1,\n" //
                + "    \"b\": {\n" //
                + "        \"c\": 2,\n" //
                + "        \"d\": 3\n" //
                + "    }\n" //
                + "}", json);
    }

    @Test
    public void testIndent2() {
        String json = personFeature.toString(4);
        assertEquals("{\n" //
                + "    \"features\": [\n" //
                + "        \"hot\",\n" //
                + "        123,\n" //
                + "        \"lazy\",\n" //
                + "        \"sexy\"\n" //
                + "    ],\n" //
                + "    \"person\": {\n" //
                + "        \"sex\": true,\n" //
                + "        \"name\": \"lenik\",\n" //
                + "        \"age\": 13\n" //
                + "    }\n" //
                + "}", json);
    }

    @Test
    public void testToMap() {
        Map<String, Object> map = JsonFn.toMap(abc);

        Map<String, Object> expected = new HashMap<>();
        expected.put("a", 1);
        Map<String, Object> expected_b = new HashMap<>();
        expected_b.put("c", 2);
        expected_b.put("d", 3);
        expected.put("b", expected_b);

        assertEquals(expected, map);
    }

}

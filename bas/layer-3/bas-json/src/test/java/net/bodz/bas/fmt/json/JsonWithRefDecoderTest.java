package net.bodz.bas.fmt.json;

import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;

public class JsonWithRefDecoderTest {

    @Test
    public void test()
            throws ParseException {
        String json = "\n"//
                + "{\n"//
                + "  \"id\": 1,\n"//
                + "  \"self\": \"<ref:>\",\n"//
                + "  \"child\": {\n"//
                + "    \"id\": 2,\n"//
                + "    \"self\": \"<ref:child>\",\n"//
                + "    \"parent\": \"<ref:>\",\n"//
                + "    \"array\": [\n"//
                + "      \"n1: \",\n"//
                + "      \"<ref:>\",\n"//
                + "      \"n2: \",\n"//
                + "      \"<ref:child>\",\n"//
                + "      \"self: \",\n"//
                + "      \"<ref:child/array>\",\n"//
                + "      \"end\"\n"//
                + "    ]\n"//
                + "  },\n"//
                + "  \"array\": \"<ref:child/array>\"\n"//
                + "}";//
        JsonObject jo = JsonFn.parseObject(json);
        JsonObject withRef = JsonWithRef.decode(jo);

        JsonWithRefEncoder encoder = new JsonWithRefEncoder() {
            @Override
            String encodeRef(String path) {
                return "\\ref{" + (path == null ? "" : path) + "}";
            }
        };

        JsonObject repack = encoder.encode(withRef).getObject();
        System.out.println(repack);
    }

}

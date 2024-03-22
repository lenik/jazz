package net.bodz.bas.fmt.json;

import java.util.IdentityHashMap;
import java.util.Map;

import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class JsonWithRefEncoder {

    final Map<Object, String> nodePathMap = new IdentityHashMap<>();

    static final String PATH_ROOT = null;

    public JsonVariant encode(JsonVariant jv) {
        return encode(jv, PATH_ROOT);
    }

    JsonVariant encode(JsonVariant jv, String path) {
        switch (jv.type) {
        case NULL:
            return JsonVariant.NULL;
        case OBJECT:
            return encode(jv.getObject(), path);
        case ARRAY:
            return encode(jv.getArray(), path);
        case SCALAR:
            return jv;
        }
        throw new Error();
    }

    public JsonVariant encode(Object val) {
        return encode(val, PATH_ROOT);
    }

    JsonVariant encode(Object val, String path) {
        if (val == null)
            return JsonVariant.NULL;
        if (val instanceof JsonObject)
            return encode((JsonObject) val, path);
        if (val instanceof JsonArray)
            return encode((JsonArray) val, path);
        return JsonVariant.of(val);
    }

    public JsonVariant encode(JsonObject node) {
        return encode(node, PATH_ROOT);
    }

    JsonVariant encode(JsonObject node, String path) {
        if (node == null)
            return JsonVariant.NULL;

        JsonVariant prevRef = addNode(node, path);
        if (prevRef != null)
            return prevRef;

        JsonObject dst = new JsonObject();
        if (! node.isEmpty()) {
            PrefixPathBuilder prefix = new PrefixPathBuilder(path);
            for (String key : node.keySet()) {
                Object val = node.get(key);
                JsonVariant val2 = encode(val, prefix.append(key));
                dst.put(key, val2.value);
            }
        }
        return JsonVariant.of(dst);
    }

    public JsonVariant encode(JsonArray node) {
        return encode(node, PATH_ROOT);
    }

    JsonVariant encode(JsonArray node, String path) {
        if (node == null)
            return JsonVariant.NULL;

        JsonVariant prevRef = addNode(node, path);
        if (prevRef != null)
            return prevRef;

        JsonArray dst = new JsonArray();
        if (! node.isEmpty()) {
            int n = node.length();
            PrefixPathBuilder prefix = new PrefixPathBuilder(path);
            for (int index = 0; index < n; index++) {
                Object val = node.get(index);
                JsonVariant val2 = encode(val, prefix.append(index));
                dst.put(index, val2.value);
            }
        }
        return JsonVariant.of(dst);
    }

    JsonVariant addNode(Object node, String path) {
        String prevPath = nodePathMap.get(node);
        if (prevPath != null || nodePathMap.containsKey(node)) {
            String refString = encodeRef(prevPath);
            return JsonVariant.of(refString);
        }
        nodePathMap.put(node, path);
        return null;
    }

    String encodeRef(String path) {
        StringBuilder sb = new StringBuilder(30);
        sb.append("<ref:");
        if (path != null)
            sb.append(path);
        sb.append(">");
        return sb.toString();
    }

}

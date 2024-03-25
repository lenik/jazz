package net.bodz.bas.fmt.json;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.json.JsonObject;

public class JsonWithRefDecoder {

    public JsonVariant decode(Object any) {
        return decode(JsonVariant.of(any));
    }

    public JsonVariant decode(JsonVariant jv) {
        switch (jv.type) {
        case NULL:
            return JsonVariant.NULL;
        case OBJECT:
            return decode(JsonVariant.object(), jv.getObject(), null);
        case ARRAY:
            return decode(JsonVariant.array(), jv.getArray(), null);
        case SCALAR:
        default:
            return jv;
        }
    }

    public JsonObject decodeObject(JsonObject jo) {
        JsonCloner cloner = new JsonCloner().ignoreRefs();
        JsonVariant root = JsonVariant.of(cloner.copyObject(jo));
        return decode(root, jo, null).getObject();
    }

    public JsonArray decodeArray(JsonArray ja) {
        JsonCloner cloner = new JsonCloner().ignoreRefs();
        JsonVariant root = JsonVariant.of(cloner.copyArray(ja));
        return decode(root, ja, null).getArray();
    }

    public JsonVariant decode(JsonVariant root, Object val, String path) {
        if (val == null)
            return JsonVariant.NULL;
        if (val instanceof JsonObject)
            return decode(root, (JsonObject) val, path);
        if (val instanceof JsonArray)
            return decode(root, (JsonArray) val, path);
        return decodeValue(root, val);
    }

    JsonVariant decode(JsonVariant root, JsonObject encoded, String path) {
        if (encoded == null)
            return JsonVariant.NULL;
        JsonObject decoded = resolve(root, path).getObject();
        if (! encoded.isEmpty()) {
            PrefixPathBuilder prefix = new PrefixPathBuilder(path);
            for (String key : encoded.keySet()) {
                Object valEncoded = encoded.get(key);
                JsonVariant valDecoded = decode(root, valEncoded, prefix.childPath(key));
                decoded.put(key, valDecoded.value);
            }
        }
        return JsonVariant.of(decoded);
    }

    JsonVariant decode(JsonVariant root, JsonArray encoded, String path) {
        if (encoded == null)
            return JsonVariant.NULL;
        JsonArray decoded = resolve(root, path).getArray();
        if (! encoded.isEmpty()) {
            PrefixPathBuilder prefix = new PrefixPathBuilder(path);
            int n = encoded.length();
            for (int index = 0; index < n; index++) {
                Object val = encoded.get(index);
                JsonVariant val2 = decode(root, val, prefix.childPath(index));
                decoded.put(index, val2.value);
            }
        }
        return JsonVariant.of(decoded);
    }

    JsonVariant decodeScalar(JsonVariant root, JsonVariant jv) {
        Object value = jv.getScalar();
        if (value instanceof String)
            return decodeString(root, value.toString());
        else
            return jv;
    }

    JsonVariant decodeValue(JsonVariant root, Object value) {
        if (value instanceof String)
            return decodeString(root, value.toString());
        else
            return JsonVariant.of(value);
    }

    JsonVariant decodeString(JsonVariant root, String str) {
        if (str.startsWith("<ref:") && str.endsWith(">")) {
            String refPath = str.substring(5, str.length() - 1);
            return resolve(root, refPath);
        }
        return JsonVariant.of(str);
    }

    static JsonVariant resolve(JsonVariant node, String path) {
        if (node == null)
            throw new NullPointerException("node, path=" + path);
        if (path == null || path.isEmpty())
            return node;

        if (path.startsWith("[")) {
            int rightBracket = path.indexOf("]");
            if (rightBracket == -1)
                throw new IllegalArgumentException("unmatched bracket: " + path);
            String indexStr = path.substring(1, rightBracket);
            int index = Integer.parseInt(indexStr);
            JsonArray array = node.getArray();
            JsonVariant item = array.getVariant(index);
            String tail = path.substring(rightBracket + 1);
            if (tail.startsWith("/"))
                tail = tail.substring(1);
            if (item == null)
                throw new UnexpectedException();
            return resolve(item, tail);
        }

        String head, tail;
        int punct = path.indexOf('/');
        if (punct == -1) {
            head = path;
            tail = null;
        } else {
            head = path.substring(0, punct);
            tail = path.substring(punct + 1);
        }
        JsonObject object = node.getObject();
        JsonVariant value = object.getVariant(head);
        if (value == null)
            throw new UnexpectedException();
        return resolve(value, tail);
    }

}

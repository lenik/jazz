package net.bodz.bas.json;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.fmt.json.JsonVariant;

public class JsonVariantTreeResolver {

    public static JsonVariant resolve(JsonVariant node, String path) {
        String[] entries = path.split("/");
        return resolve(node, entries);
    }

    public static JsonVariant resolve(JsonVariant node, String[] entryNames) {
        JsonVariantTreeResolveResult result = resolveVerbose(node, entryNames);
        if (result.status != JsonVariantTreeResolveStatus.OK) {
            throw new IllegalArgumentException(result.status.getMessage());
        }
        assert result.validCount == entryNames.length;
        return result.stoppedAt;
    }

    public static JsonVariantTreeResolveResult resolveVerbose(JsonVariant node, String[] entryNames) {
        JsonVariantTreeResolveResult result = new JsonVariantTreeResolveResult();

        for (int i = 0; i < entryNames.length; i++) {
            String entryName = entryNames[i];
            JsonVariant next;

            if (node == null) {
                result.status = JsonVariantTreeResolveStatus.INTO_NULL;
                break;
            }

            if (node.isObject()) {
                JsonObject jo = node.getObject();
                if (! jo.containsKey(entryName)) {
                    result.status = JsonVariantTreeResolveStatus.NO_KEY;
                    break;
                }
                next = jo.getVariant(entryName);
            }

            else if (node.isArray()) {
                JsonArray ja = node.getArray();
                if (! StringPred.isDecimal(entryName)) {
                    result.status = JsonVariantTreeResolveStatus.BAD_INDEX;
                    break;
                }
                int index = Integer.parseInt(entryName);
                if (index < 0 || index >= ja.length()) {
                    result.status = JsonVariantTreeResolveStatus.INDEX_OOBE;
                    break;
                }
                next = ja.getVariant(index);
            }

            else {
                result.status = JsonVariantTreeResolveStatus.INTO_NONJSON;
                break;
            }

            node = next;
            result.validCount++;
        }
        result.stoppedAt = node;
        return result;
    }

}

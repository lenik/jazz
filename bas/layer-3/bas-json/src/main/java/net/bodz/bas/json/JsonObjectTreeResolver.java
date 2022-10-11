package net.bodz.bas.json;

import net.bodz.bas.c.string.StringPred;

public class JsonObjectTreeResolver {

    public static Object resolve(Object node, String path) {
        String[] entries = path.split("/");
        return resolve(node, entries);
    }

    public static Object resolve(Object node, String[] entryNames) {
        JsonObjectTreeResolveResult result = resolveVerbose(node, entryNames);
        if (result.status != JsonObjectTreeResolveStatus.NONE) {
            throw new IllegalArgumentException(result.status.getMessage());
        }
        assert result.validCount == entryNames.length;
        return result.stoppedAt;
    }

    public static JsonObjectTreeResolveResult resolveVerbose(Object node, String[] entryNames) {
        JsonObjectTreeResolveResult result = new JsonObjectTreeResolveResult();

        for (int i = 0; i < entryNames.length; i++) {
            String entryName = entryNames[i];
            Object next;

            if (node == null) {
                result.status = JsonObjectTreeResolveStatus.INTO_NULL;
                break;
            }

            if (node instanceof JsonObject) {
                JsonObject jo = (JsonObject) node;
                if (!jo.containsKey(entryName)) {
                    result.status = JsonObjectTreeResolveStatus.NO_KEY;
                    break;
                }
                next = jo.get(entryName);
            }

            else if (node instanceof JsonArray) {
                JsonArray ja = (JsonArray) node;
                if (!StringPred.isDecimal(entryName)) {
                    result.status = JsonObjectTreeResolveStatus.BAD_INDEX;
                    break;
                }
                int index = Integer.parseInt(entryName);
                if (index < 0 || index >= ja.length()) {
                    result.status = JsonObjectTreeResolveStatus.INDEX_OOBE;
                    break;
                }
                next = ja.get(index);
            }

            else {
                result.status = JsonObjectTreeResolveStatus.INTO_NONJSON;
                break;
            }

            node = next;
            result.validCount++;
        }
        result.stoppedAt = node;
        return result;
    }

}

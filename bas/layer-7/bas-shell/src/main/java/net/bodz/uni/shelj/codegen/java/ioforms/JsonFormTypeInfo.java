package net.bodz.uni.shelj.codegen.java.ioforms;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.json.JsonObject;

public class JsonFormTypeInfo {

    boolean hasFrom_JoKey;

    static final Map<Class<?>, JsonFormTypeInfo> map = new HashMap<>();

    public static JsonFormTypeInfo ofClass(Class<?> clazz) {
        JsonFormTypeInfo info = map.get(clazz);
        if (info == null)
            map.put(clazz, info = loadInfo(clazz));
        return info;
    }

    static JsonFormTypeInfo loadInfo(Class<?> clazz) {
        JsonFormTypeInfo info = new JsonFormTypeInfo();
        try {
            clazz.getMethod("from", JsonObject.class, String.class);
            info.hasFrom_JoKey = true;
        } catch (NoSuchMethodException e) {
        }
        return info;
    }

}

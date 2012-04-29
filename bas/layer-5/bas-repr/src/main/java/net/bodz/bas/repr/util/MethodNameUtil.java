package net.bodz.bas.repr.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.repr.MethodNames;

public class MethodNameUtil {

    static Map<String, String> reverseMap;
    static {
        reverseMap = new HashMap<String, String>();

        for (Field field : MethodNames.class.getFields()) {
            int modifiers = field.getModifiers();
            if (!Modifier.isPublic(modifiers))
                continue;

            if (!Modifier.isStatic(modifiers))
                continue;

            String name = field.getName();
            String value;
            try {
                value = (String) field.get(null);
            } catch (Exception e) {
                throw new UnexpectedException("read static field " + field, e);
            }

            if (value != null)
                reverseMap.put(value, name);
        }
    }

    public static boolean isDefined(String name) {
        return reverseMap.containsKey(name);
    }

}
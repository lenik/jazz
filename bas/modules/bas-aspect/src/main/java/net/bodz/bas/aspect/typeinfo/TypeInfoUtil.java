package net.bodz.bas.aspect.typeinfo;

import java.lang.reflect.Method;

public class TypeInfoUtil {

    public static TypeInfo getTypeInfo(Class<?> type) {
        try {
            // XXX: method name configurable in annotation?
            Method method = null;
            while (true) {
                try {
                    method = type.getDeclaredMethod("getTypeInfo");
                    break;
                } catch (NoSuchMethodException e) {
                    type = type.getSuperclass();
                    if (type == null || type == Object.class)
                        return null;
                }
            }
            method.setAccessible(true);
            TypeInfo typeInfo = (TypeInfo) method.invoke(null);
            return typeInfo;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}

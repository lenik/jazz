package net.bodz.bas.annotation.xa;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XClass {

    private final Class<?> declaringType;

    public XClass(Class<?> declaringType) {
        if (declaringType == null)
            throw new NullPointerException("declaringType");
        this.declaringType = declaringType;
    }

    public Class<?> getDeclaringType() {
        return declaringType;
    }

    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        throw new RuntimeException("Not implemented");
    }

    public <A extends Annotation> A getFieldAnnotation(Field field, Class<A> annotationType) {
        throw new RuntimeException("Not implemented");
    }

    public <A extends Annotation> A getMethodAnnotation(Method method, Class<A> annotationType) {
        throw new RuntimeException("Not implemented");
    }

    private static Map<Class<?>, XClass> parsedTypes = new HashMap<Class<?>, XClass>();

    public static XClass getXClass(Class<?> type) {
        XClass xClass = parsedTypes.get(type);
        if (xClass == null) {
            xClass = new XClass(type);
            parsedTypes.put(type, xClass);
        }
        return xClass;
    }

}

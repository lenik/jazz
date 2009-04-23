package net.bodz.bas.lang.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class MethodParameter extends AccessibleObject {

    private final int          index;
    private final Class<?>     type;
    private final String       name;
    // private Method declaringMethod;
    private final Annotation[] annotations;

    public MethodParameter(int paramIndex, String paramName, Class<?> type, Annotation[] annotations) {
        this.index = paramIndex;
        this.name = paramName;
        this.type = type;
        this.annotations = annotations;
    }

    public MethodParameter(int paramIndex, Class<?> type, Annotation[] annotations) {
        this(paramIndex, // "$" +
                String.valueOf(paramIndex), type, annotations);
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Annotation[] getAnnotations() {
        // return Arrays2.copyOf(annotations);
        return annotations;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        for (int i = 0; i < annotations.length; i++) {
            Annotation a = annotations[i];
            if (annotationClass.isInstance(a))
                return annotationClass.cast(a);
        }
        return null;
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        for (int i = 0; i < annotations.length; i++) {
            Annotation a = annotations[i];
            if (annotationClass.isInstance(a))
                return true;
        }
        return false;
    }

    public static MethodParameter[] getParameters(Method method) {
        Class<?>[] ptv = method.getParameterTypes();
        Annotation[][] pav = method.getParameterAnnotations();
        assert ptv.length == pav.length;
        MethodParameter[] params = new MethodParameter[ptv.length];
        for (int i = 0; i < ptv.length; i++) {
            params[i] = new MethodParameter(i, ptv[i], pav[i]);
        }
        return params;
    }

    public static MethodParameter getParameter(Method method, int paramIndex) {
        Class<?>[] ptv = method.getParameterTypes();
        Annotation[][] pav = method.getParameterAnnotations();
        MethodParameter param = new MethodParameter(paramIndex, ptv[paramIndex], pav[paramIndex]);
        return param;
    }

}

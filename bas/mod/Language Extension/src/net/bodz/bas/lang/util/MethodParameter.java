package net.bodz.bas.lang.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class MethodParameter extends AccessibleObject {

    private final int          index;
    private final String       name;
    // private Method declaringMethod;
    private final Annotation[] annotations;

    public MethodParameter(int paramIndex, String name, Annotation[] annotations) {
        this.index = paramIndex;
        this.name = name;
        this.annotations = annotations;
    }

    public MethodParameter(int paramIndex, Annotation[] annotations) {
        this(paramIndex, "$" + paramIndex, annotations);
    }

    public String getName() {
        return name;
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
    public boolean isAnnotationPresent(
            Class<? extends Annotation> annotationClass) {
        for (int i = 0; i < annotations.length; i++) {
            Annotation a = annotations[i];
            if (annotationClass.isInstance(a))
                return true;
        }
        return false;
    }

    public static MethodParameter[] getParameters(Method method) {
        Annotation[][] av = method.getParameterAnnotations();
        MethodParameter[] params = new MethodParameter[av.length];
        for (int i = 0; i < av.length; i++)
            params[i] = new MethodParameter(i, av[i]);
        return params;
    }

    public static MethodParameter getParameter(Method method, int paramIndex) {
        Annotation[][] av = method.getParameterAnnotations();
        MethodParameter param = new MethodParameter(paramIndex, av[paramIndex]);
        return param;
    }

}

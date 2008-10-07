package net.bodz.bas.types.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.err.UnexpectedException;

/**
 * Inherited Annotations
 */
public class Ns {

    public static String getName(AnnotatedElement elm) {
        if (elm instanceof Class)
            return ((Class<?>) elm).getName();
        assert elm instanceof Member;
        return ((Member) elm).getName();
    }

    public static AnnotatedElement getParent(AnnotatedElement elm) {
        if (elm instanceof Class)
            return ((Class<?>) elm).getSuperclass();

        assert elm instanceof Member;
        Member member = ((Member) elm);
        Class<?> _super = member.getDeclaringClass().getSuperclass();
        if (member instanceof Field) {
            Field f = (Field) member;
            try {
                Field parent = _super.getDeclaredField(f.getName());
                return parent;
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new UnexpectedException(e);
            }
        }
        if (member instanceof Method) {
            Method m = (Method) member;
            try {
                Method parent = _super.getDeclaredMethod(m.getName(), m
                        .getParameterTypes());
                return parent;
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        // assert member instanceof Constructor;
        return null;
    }

    public static <A extends Annotation> A getN(AnnotatedElement elm,
            Class<A> nClass) {
        A n = elm.getAnnotation(nClass);
        if (n == null) {
            AnnotatedElement parent = getParent(elm);
            if (parent == null)
                return null;
            return getN(parent, nClass);
        }
        return n;
    }

    public static <A extends Annotation> Object getValue(AnnotatedElement elm,
            Class<A> nClass) {
        A n = getN(elm, nClass);
        if (n == null)
            return null;
        try {
            Method valuef = nClass.getMethod("value");
            // Control.invoke: value() never throws Control.
            return valuef.invoke(n);
        } catch (NoSuchMethodException e) {
            throw new IllegalUsageError(e);
        } catch (Exception e) {
            throw new Error(e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T, A extends Annotation> T _getValue(AnnotatedElement elm,
            Class<A> nClass) {
        return (T) getValue(elm, nClass);
    }

}

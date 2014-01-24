package net.bodz.bas.c.reflect;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

public class AnnotatedElements {

    public static final NullAnnotatedElement NULL = new NullAnnotatedElement();

    public static String getName(AnnotatedElement element) {
        if (element instanceof Class<?>)
            return ((Class<?>) element).getName();
        if (element instanceof Member) {
            Member member = (Member) element;
            return member.getName();
        }
        return null;
    }

}

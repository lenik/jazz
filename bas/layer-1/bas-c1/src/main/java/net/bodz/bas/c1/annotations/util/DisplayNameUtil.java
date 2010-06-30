package net.bodz.bas.c1.annotations.util;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;

import net.bodz.bas.c1.annotations.DisplayName;

public class DisplayNameUtil {

    public static String getDisplayName(AnnotatedElement annotatedElement) {
        DisplayName _name = annotatedElement.getAnnotation(DisplayName.class);
        if (_name == null)
            return null;
        String name = _name.value();
        return name;
    }

    public static String getDisplayName(Class<?> clazz) {
        String name = getDisplayName(clazz);
        if (name == null)
            name = clazz.getSimpleName();
        return name;
    }

    public static <AnnotatedMember extends AnnotatedElement & Member> //
    String getMemberDisplayName(AnnotatedMember member) {
        String name = getDisplayName(member);
        if (name == null)
            name = member.getName();
        return name;
    }

}

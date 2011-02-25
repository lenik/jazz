package net.bodz.bas.meta.info;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

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

    public static String getClassStemName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();

        Class<?> origin = clazz.getSuperclass();
        String originName = null;
        while (origin != null) {
            originName = origin.getSimpleName();

            if (Modifier.isAbstract(origin.getModifiers())) {
                if (originName.startsWith("Abstract"))
                    originName = originName.substring("Abstract".length());

                if (simpleName.endsWith(originName))
                    break;
            }

            // for (Annotation annotation : origin.getDeclaredAnnotations())
            // if (annotation instanceof PublicOrigin)
            // break;

            origin = origin.getSuperclass();
        }

        String stemName;
        if (originName != null && simpleName.endsWith(originName))
            stemName = simpleName.substring(0, simpleName.length() - originName.length());
        else
            stemName = simpleName;

        return stemName;
    }

}

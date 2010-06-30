package net.bodz.bas.c1.annotations.util;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.c1.annotations.Language;

/**
 * @test LanguageUtilTest
 */
public class LanguageUtil
        extends TagStyleAnnotationUtil {

    private static final ValueFunction<Language> valuef = new ValueFunction<Language>() {
        @Override
        public String[] value(Language annotation) {
            return annotation.value();
        }
    };

    public static String[] getLanguages(AnnotatedElement annotatedElement) {
        return getTags(Language.class, valuef, annotatedElement);
    }

    public static String[] getClassLanguages(Class<?> clazz, boolean mergeAllOccurences, boolean reverseOrder,
            boolean unique) {
        return getClassTags(Language.class, valuef, clazz, mergeAllOccurences, reverseOrder, unique);
    }

    public static String[] getClassLanguages(Class<?> clazz) {
        return getClassLanguages(clazz, true, false, true);
    }

    public static String getClassLanguages(Class<?> clazz, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] tags = getClassLanguages(clazz);
        return join(tags, separator);
    }

    public static String getLanguages(AnnotatedElement annotatedElement, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] tags = getLanguages(annotatedElement);
        if (tags.length == 0)
            return null;
        return join(tags, separator);
    }

}

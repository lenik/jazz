package net.bodz.bas.meta.info;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.annotation.util.TagStyleAnnotationUtil;

/**
 * @test TagsUtilTest
 */
public class TagsUtil
        extends TagStyleAnnotationUtil {

    private static final ValueFunction<Tags> valuef = new ValueFunction<Tags>() {
        @Override
        public String[] value(Tags annotation) {
            return annotation.value();
        }
    };

    public static String[] getTags(AnnotatedElement annotatedElement) {
        return getTags(Tags.class, valuef, annotatedElement);
    }

    public static String[] getClassTags(Class<?> clazz, boolean mergeAllOccurences, boolean reverseOrder, boolean unique) {
        return getClassTags(Tags.class, valuef, clazz, mergeAllOccurences, reverseOrder, unique);
    }

    public static String[] getClassTags(Class<?> clazz) {
        return getClassTags(clazz, true, false, true);
    }

    public static String getClassTags(Class<?> clazz, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] tags = getClassTags(clazz);
        return join(tags, separator);
    }

    public static String getTags(AnnotatedElement annotatedElement, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] tags = getTags(annotatedElement);
        if (tags.length == 0)
            return null;
        return join(tags, separator);
    }

}

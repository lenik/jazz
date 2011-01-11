package net.bodz.bas.c1.annotations.util;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.c1.annotations.Author;

/**
 * @test AuthorUtilTest
 */
public class AuthorUtil
        extends TagStyleAnnotationUtil {

    private static final ValueFunction<Author> valueF = new ValueFunction<Author>() {
        @Override
        public String[] value(Author annotation) {
            return annotation.value();
        }
    };

    public static String[] getAuthors(AnnotatedElement annotatedElement) {
        return getTags(Author.class, valueF, annotatedElement);
    }

    public static String[] getClassAuthors(Class<?> clazz, boolean mergeAllOccurences, boolean reverseOrder,
            boolean unique) {
        return getClassTags(Author.class, valueF, clazz, mergeAllOccurences, reverseOrder, unique);
    }

    public static String[] getClassAuthors(Class<?> clazz) {
        return getClassAuthors(clazz, true, false, true);
    }

    public static String getClassAuthors(Class<?> clazz, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] authors = getClassAuthors(clazz);
        return join(authors, separator);
    }

    public static String getAuthors(AnnotatedElement annotatedElement, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] authors = getAuthors(annotatedElement);
        if (authors.length == 0)
            return null;
        return join(authors, separator);
    }

}

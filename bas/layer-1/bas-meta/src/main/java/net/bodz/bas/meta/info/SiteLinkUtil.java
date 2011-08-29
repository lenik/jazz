package net.bodz.bas.meta.info;

import java.lang.reflect.AnnotatedElement;

import net.bodz.bas.util.annotation.TagStyleAnnotationUtil;

/**
 * @test SiteLinkUtilTest
 */
public class SiteLinkUtil
        extends TagStyleAnnotationUtil {

    private static final ValueFunction<SiteLink> valuef = new ValueFunction<SiteLink>() {
        @Override
        public String[] value(SiteLink annotation) {
            return annotation.value();
        }
    };

    public static String[] getSiteLinks(AnnotatedElement annotatedElement) {
        return getTags(SiteLink.class, valuef, annotatedElement);
    }

    public static String[] getClassSiteLinks(Class<?> clazz, boolean mergeAllOccurences, boolean reverseOrder,
            boolean unique) {
        return getClassTags(SiteLink.class, valuef, clazz, mergeAllOccurences, reverseOrder, unique);
    }

    public static String[] getClassSiteLinks(Class<?> clazz) {
        return getClassSiteLinks(clazz, true, false, true);
    }

    public static String getClassSiteLinks(Class<?> clazz, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] links = getClassSiteLinks(clazz);
        return join(links, separator);
    }

    public static String getSiteLinks(AnnotatedElement annotatedElement, String separator) {
        if (separator == null)
            throw new NullPointerException("separator");
        String[] links = getSiteLinks(annotatedElement);
        if (links.length == 0)
            return null;
        return join(links, separator);
    }

}

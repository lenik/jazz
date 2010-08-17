package net.bodz.bas.c1.annotations.util;

import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;
import net.bodz.bas.c1.annotations.SiteLink;

import org.junit.Test;

public class SiteLinkUtilTest
        extends TestCase {

    @SiteLink({ "http://example.com/1", "http://example.com/2" })
    static class ClassA {

    }

    static class ClassA1
            extends ClassA {

    }

    @SiteLink({ "http://example.com/3", "http://example.com/2" })
    static class ClassA1i
            extends ClassA1 {

    }

    @Test
    public void testGetSiteLinks_NotDeclared() {
        String[] links = SiteLinkUtil.getSiteLinks(String.class);
        assertNotNull(links);
        assertEquals(0, links.length);
    }

    @Test
    public void testGetSiteLinks_Declared() {
        String[] links = SiteLinkUtil.getSiteLinks(ClassA1.class);
        String[] expected = { "http://example.com/1", "http://example.com/2" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetSiteLinks_Inherited() {
        String[] links = SiteLinkUtil.getSiteLinks(ClassA1.class);
        String[] expected = { "http://example.com/1", "http://example.com/2" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetClassSiteLinks_Declared() {
        String[] links = SiteLinkUtil.getClassSiteLinks(ClassA.class, false, false, false);
        String[] expected = { "http://example.com/1", "http://example.com/2" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetClassSiteLinks_AllOccurences() {
        String[] links = SiteLinkUtil.getClassSiteLinks(ClassA1.class, true, false, false);
        String[] expected = { "http://example.com/1", "http://example.com/2" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetClassSiteLinks_AllOccurences2() {
        String[] links = SiteLinkUtil.getClassSiteLinks(ClassA1i.class, true, false, false);
        String[] expected = { "http://example.com/1", "http://example.com/2", "http://example.com/3",
                "http://example.com/2" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetClassSiteLinks_AllOccurences_Unique() {
        String[] links = SiteLinkUtil.getClassSiteLinks(ClassA1i.class, true, false, true);
        String[] expected = { "http://example.com/1", "http://example.com/2", "http://example.com/3" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetClassSiteLinks_AllOccurences_Reversed() {
        String[] links = SiteLinkUtil.getClassSiteLinks(ClassA1i.class, true, true, false);
        String[] expected = { "http://example.com/3", "http://example.com/2", "http://example.com/1",
                "http://example.com/2" };
        assertArrayEquals(expected, links);
    }

    @Test
    public void testGetClassSiteLinks_AllOccurences_Reversed_Unique() {
        String[] links = SiteLinkUtil.getClassSiteLinks(ClassA1i.class, true, true, true);
        String[] expected = { "http://example.com/3", "http://example.com/2", "http://example.com/1" };
        assertArrayEquals(expected, links);
    }

    public static void main(String[] args) {
        SiteLink _link = ClassA1i.class.getAnnotation(SiteLink.class);
        if (_link != null) {
            for (String s : _link.value())
                System.out.println("SiteLink: " + s);
        }
    }

}

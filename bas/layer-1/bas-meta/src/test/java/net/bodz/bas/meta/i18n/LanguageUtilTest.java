package net.bodz.bas.meta.i18n;

import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;
import net.bodz.bas.meta.i18n.Language;
import net.bodz.bas.meta.i18n.LanguageUtil;

import org.junit.Test;

public class LanguageUtilTest
        extends TestCase {

    @Language({ "Lang1", "Lang2" })
    static class ClassA {

    }

    static class ClassA1
            extends ClassA {

    }

    @Language({ "Lang3", "Lang2" })
    static class ClassA1i
            extends ClassA1 {

    }

    @Test
    public void testGetLanguages_NotDeclared() {
        String[] langs = LanguageUtil.getLanguages(String.class);
        assertNotNull(langs);
        assertEquals(0, langs.length);
    }

    @Test
    public void testGetLanguages_Declared() {
        String[] langs = LanguageUtil.getLanguages(ClassA1.class);
        String[] expected = { "Lang1", "Lang2" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetLanguages_Inherited() {
        String[] langs = LanguageUtil.getLanguages(ClassA1.class);
        String[] expected = { "Lang1", "Lang2" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetClassLanguages_Declared() {
        String[] langs = LanguageUtil.getClassLanguages(ClassA.class, false, false, false);
        String[] expected = { "Lang1", "Lang2" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetClassLanguages_AllOccurences() {
        String[] langs = LanguageUtil.getClassLanguages(ClassA1.class, true, false, false);
        String[] expected = { "Lang1", "Lang2" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetClassLanguages_AllOccurences2() {
        String[] langs = LanguageUtil.getClassLanguages(ClassA1i.class, true, false, false);
        String[] expected = { "Lang1", "Lang2", "Lang3", "Lang2" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetClassLanguages_AllOccurences_Unique() {
        String[] langs = LanguageUtil.getClassLanguages(ClassA1i.class, true, false, true);
        String[] expected = { "Lang1", "Lang2", "Lang3" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetClassLanguages_AllOccurences_Reversed() {
        String[] langs = LanguageUtil.getClassLanguages(ClassA1i.class, true, true, false);
        String[] expected = { "Lang3", "Lang2", "Lang1", "Lang2" };
        assertArrayEquals(expected, langs);
    }

    @Test
    public void testGetClassLanguages_AllOccurences_Reversed_Unique() {
        String[] langs = LanguageUtil.getClassLanguages(ClassA1i.class, true, true, true);
        String[] expected = { "Lang3", "Lang2", "Lang1" };
        assertArrayEquals(expected, langs);
    }

    public static void main(String[] args) {
        Language _lang = ClassA1i.class.getAnnotation(Language.class);
        if (_lang != null) {
            for (String s : _lang.value())
                System.out.println("Language: " + s);
        }
    }

}

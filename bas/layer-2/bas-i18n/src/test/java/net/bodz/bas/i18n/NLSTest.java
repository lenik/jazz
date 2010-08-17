package net.bodz.bas.i18n;

import java.util.Locale;

import junit.framework.TestCase;
import net.bodz.bas.i18n.nls_2.ResourceBundleNLS;

import org.junit.Test;

public class NLSTest
        extends TestCase {

    ResourceBundleNLS NLS_zh_CN_var;

    public NLSTest() {
        NLS_zh_CN_var = new ResourceBundleNLS(NLSTest.class, new Locale("zh", "CN", "var"));
    }

    @Test
    public void testLocaleOverrideDefault() {
        assertEquals("value0", NLS_zh_CN_var.getString("key0"));
    }

    @Test
    public void testLocaleOverrideLang() {
        assertEquals("zh1", NLS_zh_CN_var.getString("key1"));
    }

    @Test
    public void testLocaleOverrideCountry() {
        assertEquals("zh_CN2", NLS_zh_CN_var.getString("key2"));
    }

    @Test
    public void testLocaleOverrideVar() {
        assertEquals("zh_CN_var3", NLS_zh_CN_var.getString("key3"));
    }

}

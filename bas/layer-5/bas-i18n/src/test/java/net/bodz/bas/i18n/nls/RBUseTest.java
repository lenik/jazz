package net.bodz.bas.i18n.nls;

import java.util.Locale;
import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * ResourceBundle use case to demonstrate multilayered locale in ResourceBundle usage.
 */
public class RBUseTest
        extends TestCase {

    ResourceBundle rb;

    public RBUseTest() {
        Locale locale = new Locale("zh", "CN", "runtime");
        rb = ResourceBundle.getBundle(RBUse.class.getName(), locale);
    }

    @Test
    public void testLocaleOverrideDefault() {
        assertEquals("value0", rb.getString("key0"));
    }

    @Test
    public void testLocaleOverrideLang() {
        assertEquals("zh1", rb.getString("key1"));
    }

    @Test
    public void testLocaleOverrideCountry() {
        assertEquals("zh_CN2", rb.getString("key2"));
    }

    @Test
    public void testLocaleOverrideRuntimeExtension() {
        assertEquals("zh_CN_runtime3", rb.getString("key3"));
    }

}

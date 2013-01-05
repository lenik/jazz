package net.bodz.bas.i18n.nls;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.util.UTF8ResourceBundle;

/**
 * ResourceBundle use case to demonstrate multilayered locale in ResourceBundle usage.
 */
public class RBUseTest
        extends Assert {

    ResourceBundle rb;

    public RBUseTest() {
        Locale locale = new Locale("zh", "CN", "runtime");
        rb = UTF8ResourceBundle.getBundle(RBUse.class.getName(), locale);
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
        assertEquals("中文2", rb.getString("key2"));
    }

    @Test
    public void testLocaleOverrideRuntimeExtension() {
        assertEquals("汉字3", rb.getString("key3"));
    }

}

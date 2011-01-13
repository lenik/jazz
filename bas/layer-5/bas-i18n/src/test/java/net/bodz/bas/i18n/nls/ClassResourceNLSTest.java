package net.bodz.bas.i18n.nls;

import static net.bodz.bas.i18n.nls.FoodNLS.FoodNLS;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

public class ClassResourceNLSTest
        extends Assert {

    static final Locale en = new Locale("en");
    static final Locale zh = new Locale("zh");
    static final Locale zh_CN = new Locale("zh", "CN");

    @Test
    public void testTr_en() {
        FoodNLS.setPreferredLocale(en);
        assertEquals("Apple", FoodNLS.tr("apple"));
        assertEquals("Pear", FoodNLS.tr("pear"));
        assertEquals("Banana", FoodNLS.tr("banana"));
    }

    @Test
    public void testTr_zh() {
        FoodNLS.setPreferredLocale(zh);
        assertEquals("Apple", FoodNLS.tr("apple"));
        assertEquals("Pear", FoodNLS.tr("pear"));
        assertEquals("Banana", FoodNLS.tr("banana"));
    }

    @Test
    public void testTr_zh_CN() {
        FoodNLS.setPreferredLocale(zh_CN);
        assertEquals("苹果", FoodNLS.tr("apple"));
        assertEquals("Pear", FoodNLS.tr("pear"));
        assertEquals("香蕉", FoodNLS.tr("banana"));
    }

}

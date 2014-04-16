package net.bodz.mda.xjdoc.conv;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * Test
 * 
 * This test only works for Maven project.
 * 
 * @label 测试
 */
public class MavenClassDocLoaderTest
        extends Assert {

    /**
     * Main Method.
     */
    @Test
    public void testClassDoc() {
        MavenClassDocLoader loader = new MavenClassDocLoader();
        ClassDoc doc = loader.load(MavenClassDocLoaderTest.class);

        iString label = doc.getLabel();
        assertEquals("测试", label.toString());

        iString text = doc.getText();
        assertEquals("Test", text.getHeadPar());
        assertEquals("This test only works for Maven project.", text.getTailPar());
    }

}

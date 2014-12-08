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
public class MavenXjdocProviderTest
        extends Assert {

    /**
     * Main Method.
     */
    @Test
    public void testClassDoc() {
        MavenXjdocProvider loader = new MavenXjdocProvider();
        ClassDoc doc = loader.getClassDoc(MavenXjdocProviderTest.class);

        iString label = (iString) doc.getTag("label");
        assertEquals("测试", label.toString());

        iString text = doc.getText();
        assertEquals("Test", text.getHeadPar());
        assertEquals("This test only works for Maven project.", text.getTailPar());
    }

}

package net.bodz.mda.xjdoc;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.mda.xjdoc.conv.MavenXjdocProvider;
import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * Test String
 */
public class XjdocsTest
        extends Assert {

    /**
     * To ensure if {@link MavenXjdocProvider} is working, clean the project and run again.
     */
    @Test
    public void testAny() {
        ClassDoc classDoc = Xjdocs.getDefaultProvider().getClassDoc(XjdocsTest.class);
        assertEquals("Test String", classDoc.getText().toString());
    }

}

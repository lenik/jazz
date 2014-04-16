package net.bodz.mda.xjdoc;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.mda.xjdoc.conv.MavenClassDocLoader;
import net.bodz.mda.xjdoc.model.ClassDoc;

/**
 * Test
 */
public class ClassDocLoaderTest
        extends Assert {

    /**
     * To ensure if {@link MavenClassDocLoader} is working, clean the project and run again.
     */
    @Test
    public void testAny() {
        ClassDoc classDoc = ClassDocLoader.load(ClassDocLoaderTest.class);
        assertEquals("Test", classDoc.getText().toString());
    }

}

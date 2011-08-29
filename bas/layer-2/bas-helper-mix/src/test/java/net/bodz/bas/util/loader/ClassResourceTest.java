package net.bodz.bas.util.loader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.util.loader.ClassResource;

public class ClassResourceTest
        extends Assert {

    @Test
    public void testClassData()
            throws Exception {
        URLResource classData = ClassResource.classData(getClass(), "data");
        String actual = classData.forRead().readTextContents();
        assertEquals("Hello, world!", actual);
    }

}

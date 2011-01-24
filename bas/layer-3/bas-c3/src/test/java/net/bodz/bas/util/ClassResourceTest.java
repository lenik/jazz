package net.bodz.bas.util;

import net.bodz.bas.io.resource.builtin.URLResource;

import org.junit.Assert;
import org.junit.Test;

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

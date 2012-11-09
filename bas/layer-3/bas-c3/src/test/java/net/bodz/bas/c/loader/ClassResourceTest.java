package net.bodz.bas.c.loader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;

public class ClassResourceTest
        extends Assert {

    @Test
    public void testClassData()
            throws Exception {
        URLResource classData = ClassResource.getData(getClass(), "data");
        String actual = classData.tooling()._for(StreamReading.class).readTextContents();
        assertEquals("Hello, world!", actual);
    }

}

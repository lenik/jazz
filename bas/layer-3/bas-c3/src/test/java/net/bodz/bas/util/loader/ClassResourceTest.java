package net.bodz.bas.util.loader;

import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;

import org.junit.Assert;
import org.junit.Test;

public class ClassResourceTest
        extends Assert {

    @Test
    public void testClassData()
            throws Exception {
        URLResource classData = ClassResource.classData(getClass(), "data");
        String actual = classData.tooling()._for(StreamReading.class).readTextContents();
        assertEquals("Hello, world!", actual);
    }

}

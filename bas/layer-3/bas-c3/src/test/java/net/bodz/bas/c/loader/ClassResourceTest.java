package net.bodz.bas.c.loader;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.res.builtin.URLResource;
import net.bodz.bas.io.res.tools.StreamReading;

public class ClassResourceTest
        extends Assert {

    @Test
    public void testGetData()
            throws Exception {
        URLResource classData = ClassResource.getData(getClass(), "data");
        String actual = classData.to(StreamReading.class).readString();
        assertEquals("Hello, world!", actual);
    }

}

package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.std.rfc.mime.StringContent;

public class ContentURLTest
        extends Assert {

    @Test
    public void testGetContent()
            throws IOException {
        StringContent helloContent = new StringContent("hello");
        URL url = ContentURL.create("/hello1", helloContent);
        assertEquals("c:/hello1", url.toExternalForm());

        List<String> lines = URLData.readUtf8Lines(url);
        assertEquals(1, lines.size());

        String line = lines.get(0);
        assertEquals("hello", line);
    }

}

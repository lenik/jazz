package net.bodz.bas.c.java.net;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class InMemoryURLTest
        extends Assert {

    @Test
    public void testGetContent()
            throws IOException {
        URL url = InMemoryURL.create("/hello1", "hello");
        assertEquals("mem:/hello1", url.toExternalForm());

        List<String> lines = URLData.readUtf8Lines(url);
        assertEquals(1, lines.size());

        String line = lines.get(0);
        assertEquals("hello", line);
    }

}

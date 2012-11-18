package net.bodz.bas.vfs.impl.url;

import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.vfs.path.IPath;

public class NestedURLPathTest
        extends Assert {

    URL url = Test.class.getResource("Test.class");
    URLPath path = URLPath.parse(url);

    @Test
    public void parsedType() {
        assertTrue(path instanceof NestedURLPath);
    }

    @Test
    public void format1() {
        String str = path.toString();
        System.out.println("Format: " + str);
    }

    @Test
    public void toURL() {
        URL other = path.toURL();
        assertEquals(url, other);
    }

    @Test
    public void createLocal()
            throws Exception {
        URL expected = Test.class.getResource("Assert.class");

        IPath path2 = path.join("Assert.class");
        URL url2 = path2.toURL();
        assertEquals(expected, url2);
    }

}

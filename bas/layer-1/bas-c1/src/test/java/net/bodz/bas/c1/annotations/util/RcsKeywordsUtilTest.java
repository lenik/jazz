package net.bodz.bas.c1.annotations.util;

import static org.junit.Assert.assertArrayEquals;
import junit.framework.TestCase;

import org.junit.Test;

public class RcsKeywordsUtilTest
        extends TestCase {

    @Test
    public void testParseId() {
        String id = "$" + "Id: example.file 1.2.3 2010-01-20 12:43:33Z lenik " + "$";
        VersionInfo ver = RcsKeywordsUtil.parseId(id);
        assertEquals("example.file", ver.name);
        assertEquals("lenik", ver.author);

        int[] expectedRevision = { 1, 2, 3 };
        assertArrayEquals(expectedRevision, ver.revision);

        String versionText = ver.getVersion();
        assertEquals("1.2.3", versionText);
    }

}

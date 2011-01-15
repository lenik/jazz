package net.bodz.bas.meta.build;

import org.junit.Assert;
import org.junit.Test;

public class RcsKeywordsUtilTest
        extends Assert {

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

package net.bodz.bas.meta.build;

import org.junit.Assert;
import org.junit.Test;

public class RcsKeywordsUtilTest
        extends Assert {

    @Test
    public void testParseId() {
        String id = "$" + "Id: example.file 1.2.3 2010-01-20 12:43:33Z lenik " + "$";
        ReleaseDescription release = RcsKeywordsUtil.parseId(id);
        assertEquals("example.file", release.name);
        assertEquals("lenik", release.author);

        String[] expectedRevision = { "1", "2", "3" };
        assertArrayEquals(expectedRevision, release.getVersion().getVersionElements());

        String versionText = release.getVersion().toString();
        assertEquals("1.2.3", versionText);
    }

}

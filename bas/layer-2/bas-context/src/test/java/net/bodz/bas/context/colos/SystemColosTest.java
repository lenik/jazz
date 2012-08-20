package net.bodz.bas.context.colos;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.context.StaticContextId;
import net.bodz.bas.vfs.SystemColos;

public class SystemColosTest
        extends Assert {

    @Test
    public void testCwd()
            throws Exception {
        File cwd = SystemColos.cwd.get(StaticContextId.getInstance());
        assert cwd != null : "null cwd";
        String cwdPath = cwd.getPath();
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, cwdPath);
    }

}

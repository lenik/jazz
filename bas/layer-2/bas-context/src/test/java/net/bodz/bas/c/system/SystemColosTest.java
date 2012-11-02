package net.bodz.bas.c.system;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.system.SystemColos;
import net.bodz.bas.context.StaticContextId;

public class SystemColosTest
        extends Assert {

    @Test
    public void testCwd()
            throws Exception {
        File cwd = SystemColos.workdir.get(StaticContextId.getInstance());
        assert cwd != null : "null cwd";
        String cwdPath = cwd.getPath();
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, cwdPath);
    }

}

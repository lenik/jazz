package net.bodz.bas.c.system;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.context.IContext;

public class UserDirCtlTest
        extends Assert {

    @Test
    public void testCwd()
            throws Exception {
        File defaultWorkdir = UserDirCtl.getInstance().get(IContext.DEFAULT);
        assert defaultWorkdir != null;
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, defaultWorkdir.getPath());
    }

}

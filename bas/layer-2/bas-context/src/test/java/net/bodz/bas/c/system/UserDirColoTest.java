package net.bodz.bas.c.system;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.context.ClassContext;
import net.bodz.bas.context.Contexts;

public class UserDirColoTest
        extends Assert {

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassContext innerContext = Contexts.caller();
                return UserDirCtl.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassContext innerContext = Contexts.caller();
                UserDirCtl.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassContext outerContext = Contexts.caller();
        File outercwd = UserDirCtl.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = UserDirCtl.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

package net.bodz.bas.c.system;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.context.ClassContextId;

public class UserDirColoTest
        extends Assert {

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassContextId innerContext = ClassContextId.getCallerClassContext();
                return UserDirColo.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassContextId innerContext = ClassContextId.getCallerClassContext();
                UserDirColo.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassContextId outerContext = ClassContextId.getCallerClassContext();
        File outercwd = UserDirColo.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = UserDirColo.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

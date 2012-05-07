package net.bodz.bas.io;

import java.io.File;

import net.bodz.bas.context.ClassContextId;
import net.bodz.bas.vfs.CurrentDirectoryColo;

import org.junit.Assert;
import org.junit.Test;

public class ContextCWDTest
        extends Assert {

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassContextId innerContext = ClassContextId.getCallerClassContext();
                return CurrentDirectoryColo.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassContextId innerContext = ClassContextId.getCallerClassContext();
                CurrentDirectoryColo.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassContextId outerContext = ClassContextId.getCallerClassContext();
        File outercwd = CurrentDirectoryColo.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = CurrentDirectoryColo.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

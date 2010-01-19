package net.bodz.bas.arch.context.sysctx;

import static org.junit.Assert.assertEquals;

import java.io.File;

import net.bodz.bas.arch.context.ClassContext;
import net.bodz.bas.arch.context.sysclg.ContextCWD;

import org.junit.Test;

public class ContextCWDTest {

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassContext innerContext = ClassContext.getCallerClassContext();
                return ContextCWD.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassContext innerContext = ClassContext.getCallerClassContext();
                ContextCWD.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassContext outerContext = ClassContext.getCallerClassContext();
        File outercwd = ContextCWD.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = ContextCWD.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

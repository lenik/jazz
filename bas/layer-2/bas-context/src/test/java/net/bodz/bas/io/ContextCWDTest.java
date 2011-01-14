package net.bodz.bas.io;

import java.io.File;

import junit.framework.TestCase;
import net.bodz.bas.context.ClassContext;

import org.junit.Test;

public class ContextCWDTest
        extends TestCase {

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassContext innerContext = ClassContext.getCallerClassContext();
                return ContextDirectory.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassContext innerContext = ClassContext.getCallerClassContext();
                ContextDirectory.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassContext outerContext = ClassContext.getCallerClassContext();
        File outercwd = ContextDirectory.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = ContextDirectory.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

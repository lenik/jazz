package net.bodz.bas.c.system;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.ClassScopeToken;
import net.bodz.bas.ctx.scope.Scopes;
import net.bodz.bas.ctx.scope.IScopeToken;

public class UserDirScrTest
        extends Assert {

    @Test
    public void testCwd()
            throws Exception {
        File defaultWorkdir = UserDirScr.getInstance().get(IScopeToken.DEFAULT);
        assert defaultWorkdir != null;
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, defaultWorkdir.getPath());
    }

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassScopeToken innerContext = Scopes.caller();
                return UserDirScr.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassScopeToken innerContext = Scopes.caller();
                UserDirScr.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassScopeToken outerContext = Scopes.caller();
        File outercwd = UserDirScr.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = UserDirScr.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

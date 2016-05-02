package net.bodz.bas.ctx.sys;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.Scopes;
import net.bodz.bas.ctx.scope.impl.ClassScopeInstance;
import net.bodz.bas.ctx.sys.UserDirVars;

public class UserDirVarsTest
        extends Assert {

    @Test
    public void testCwd()
            throws Exception {
        File defaultWorkdir = UserDirVars.getInstance().get(IScopeInstance.STATIC);
        assert defaultWorkdir != null;
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, defaultWorkdir.getPath());
    }

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            File getcwd() {
                ClassScopeInstance innerContext = Scopes.caller();
                return UserDirVars.getInstance().get(innerContext);
            }

            void chdir(File dir) {
                ClassScopeInstance innerContext = Scopes.caller();
                UserDirVars.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        File userDir = new File(System.getProperty("user.dir"));
        File innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassScopeInstance outerContext = Scopes.caller();
        File outercwd = UserDirVars.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(new File("/"));
        outercwd = UserDirVars.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

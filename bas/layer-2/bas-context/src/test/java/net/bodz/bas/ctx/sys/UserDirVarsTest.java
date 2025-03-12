package net.bodz.bas.ctx.sys;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.Scopes;
import net.bodz.bas.ctx.scope.impl.ClassScopeInstance;

public class UserDirVarsTest
        extends Assert {

    @Test
    public void testCwd()
            throws Exception {
        Path defaultWorkdir = UserDirVars.getInstance().get(IScopeInstance.STATIC);
        assert defaultWorkdir != null;
        String userDir = System.getProperty("user.dir");
        assertEquals(userDir, defaultWorkdir.toString());
    }

    @Test
    public void testInnerClassCwd()
            throws Exception {

        class Inner {
            Path getcwd() {
                ClassScopeInstance innerContext = Scopes.caller();
                return UserDirVars.getInstance().get(innerContext);
            }

            void chdir(Path dir) {
                ClassScopeInstance innerContext = Scopes.caller();
                UserDirVars.getInstance().chdir(innerContext, dir);
            }
        }

        Inner inner = new Inner();
        Path userDir = Paths.get(System.getProperty("user.dir"));
        Path innercwd = inner.getcwd();
        assertEquals(userDir, innercwd);

        ClassScopeInstance outerContext = Scopes.caller();
        Path outercwd = UserDirVars.getInstance().get(outerContext);
        assertEquals(userDir, innercwd);

        inner.chdir(Paths.get("/"));
        outercwd = UserDirVars.getInstance().get(outerContext);
        assertEquals(userDir, outercwd);
    }

}

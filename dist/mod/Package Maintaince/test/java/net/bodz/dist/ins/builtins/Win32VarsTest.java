package net.bodz.dist.ins.builtins;

import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins.TestProject;
import net.bodz.dist.ins.Variable;
import net.bodz.dist.ins.builtins.Win32Vars;

import org.junit.Test;

public class Win32VarsTest {

    @Test
    public void test() throws Exception {
        if (!SystemInfo.isWin32())
            return;

        TestProject project = new TestProject();
        Variable var = project.get(Win32Vars.COMMON_AUTOSTART_MENU);
        System.out.println("COMMON_AUTOSTART_MENU=" + var.getDefaultValue()); //$NON-NLS-1$
    }

}

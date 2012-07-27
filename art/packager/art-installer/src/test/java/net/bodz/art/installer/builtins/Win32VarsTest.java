package net.bodz.art.installer.builtins;

import net.bodz.art.installer.TestProject;
import net.bodz.art.installer.Variable;
import net.bodz.bas.c.system.SystemInfo;

import org.junit.Test;

public class Win32VarsTest {

    @Test
    public void test()
            throws Exception {
        if (!SystemInfo.isWin32())
            return;

        TestProject project = new TestProject();
        Variable var = project.get(Win32Vars.COMMON_AUTOSTART_MENU);
        System.out.println("COMMON_AUTOSTART_MENU=" + var.getDefaultValue());
    }

}

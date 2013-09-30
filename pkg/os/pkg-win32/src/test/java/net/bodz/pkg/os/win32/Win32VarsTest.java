package net.bodz.pkg.os.win32;

import org.junit.Assert;

import net.bodz.pkg.installer.IProject;
import net.bodz.pkg.installer.Variable;

public class Win32VarsTest
        extends Assert {

    public void main()
            throws Exception {
        IProject project = null;
        Win32Vars.setup(project);
        Variable var = project.get(Win32Vars.COMMON_AUTOSTART_MENU);
        System.out.println("COMMON_AUTOSTART_MENU=" + var.getDefaultValue());
    }

}

package net.bodz.dist.ins;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.dist.ins.Installer;

@Doc("TestApp")
// @Icon("icon.gif")
@RcsKeywords(id = "$Id: TestApp.java 8 2009-03-29 09:01:53Z Shecti $")
@Version( { 5, 6, 7, 8, })
public class InstallerTest {

    public static void main(String[] args) throws Throwable {
        String[] installerArgs = {
        //
                "-c", InstallerTest.class.getName(), //
        };
        Installer.main(installerArgs);
    }

}

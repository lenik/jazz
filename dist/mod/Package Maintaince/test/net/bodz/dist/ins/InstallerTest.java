package net.bodz.dist.ins;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;

@Doc("TestApp")
// @Icon("icon.gif")
@RcsKeywords(id = "$Id$")
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

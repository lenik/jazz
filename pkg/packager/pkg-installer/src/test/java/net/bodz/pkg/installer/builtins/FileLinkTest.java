package net.bodz.pkg.installer.builtins;

import org.junit.Test;

import net.bodz.bas.io.res.builtin.ByteArrayResource;
import net.bodz.pkg.installer.SessionException;
import net.bodz.pkg.installer.TestProject;

public class FileLinkTest
        extends ComponentTestApp {

    public FileLinkTest() {
        byte[] data1 = "A big fat black cat".getBytes();
        section.add(new CreateFile(TestProject.BASE_A, "cat", new ByteArrayResource(data1)));
        FileLink link = new FileLink(TestProject.BASE_A, "cat", //
                TestProject.BASE_B, "hard_cat", false);
        FileLink symlink = new FileLink(TestProject.BASE_A, "cat", //
                TestProject.BASE_B, "sym_cat", true);
        section.add(link);
        section.add(symlink);
    }

    @Test
    @Override
    public void testPack()
            throws SessionException {
        super.testPack();
    }

    @Test
    @Override
    public void testInstall()
            throws SessionException {
        super.testInstall();
    }

    @Test
    @Override
    public void testUninstall()
            throws SessionException {
        super.testUninstall();
    }

}
package net.bodz.art.installer.builtins;

import net.bodz.art.installer.SessionException;
import net.bodz.art.installer.TestProject;

import org.junit.Test;

public class FileLinkTest
        extends ComponentTestApp {

    public FileLinkTest() {
        section.add(new CreateFile(TestProject.BASE_A, "cat", "A big fat black cat"));
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

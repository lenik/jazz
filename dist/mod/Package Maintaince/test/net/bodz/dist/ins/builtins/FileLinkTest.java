package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.SessionException;
import net.bodz.dist.ins.TestProject;
import net.bodz.dist.ins.builtins.ComponentTestApp;
import net.bodz.dist.ins.builtins.CreateFile;
import net.bodz.dist.ins.builtins.FileLink;

import org.junit.Test;

public class FileLinkTest extends ComponentTestApp {

    public FileLinkTest() {
        section.add(new CreateFile(TestProject.BASE_A, "cat", "A big fat black cat")); //$NON-NLS-1$ //$NON-NLS-2$
        FileLink link = new FileLink(TestProject.BASE_A, "cat", // //$NON-NLS-1$
                TestProject.BASE_B, "cat link from A", false); //$NON-NLS-1$
        FileLink symlink = new FileLink(TestProject.BASE_A, "cat", // //$NON-NLS-1$
                TestProject.BASE_B, "symbolic cat link from A", true); //$NON-NLS-1$
        section.add(link);
        section.add(symlink);
    }

    @Test
    @Override
    public void testPack() throws SessionException {
        super.testPack();
    }

    @Test
    @Override
    public void testInstall() throws SessionException {
        super.testInstall();
    }

    @Test
    @Override
    public void testUninstall() throws SessionException {
        super.testUninstall();
    }

}

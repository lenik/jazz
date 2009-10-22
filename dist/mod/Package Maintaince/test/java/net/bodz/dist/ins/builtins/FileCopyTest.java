package net.bodz.dist.ins.builtins;

import java.io.File;

import net.bodz.bas.io.Files;
import net.bodz.dist.ins.SessionException;
import net.bodz.dist.ins.TestProject;

import org.junit.Test;

public class FileCopyTest extends ComponentTestApp {

    public FileCopyTest() throws Exception {
        File tmp = Files.getTmpDir();
        File localdir = new File(tmp, "localdir");
        localdir.mkdirs();
        File catfile = new File(localdir, "cat");
        Files.write(catfile, "a black fat cat. ");
        FileCopy copy = new FileCopy(TestProject.BASE_A, "copytest", localdir, catfile);
        section.add(copy);
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

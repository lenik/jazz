package net.bodz.art.installer.builtins;

import java.io.File;
import java.nio.file.Files;

import net.bodz.art.installer.SessionException;
import net.bodz.art.installer.TestProject;
import net.bodz.bas.c.java.io.TempFile;

import org.junit.Test;

public class FileCopyTest
        extends ComponentTestApp {

    public FileCopyTest()
            throws Exception {
        File tmp = TempFile.getTmpDir();
        File localdir = new File(tmp, "localdir");
        localdir.mkdirs();
        File catfile = new File(localdir, "cat");
        Files.write(catfile, "a black fat cat. ");
        FileCopy copy = new FileCopy(TestProject.BASE_A, "copytest", localdir, catfile);
        section.add(copy);
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

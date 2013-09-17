package net.bodz.pkg.installer.builtins;

import java.io.File;

import org.junit.Test;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.pkg.installer.SessionException;
import net.bodz.pkg.installer.TestProject;

public class FileCopyTest
        extends ComponentTestApp {

    public FileCopyTest()
            throws Exception {
        File tmp = TempFile.getTempRoot();
        File localdir = new File(tmp, "localdir");
        localdir.mkdirs();
        File catfile = new File(localdir, "cat");

        new PojfFile(catfile).tooling()._for(StreamWriting.class).writeString("a black fat cat. ");

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

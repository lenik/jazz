package net.bodz.redist.installer.builtins;

import java.io.File;

import org.junit.Test;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.vfs.impl.javaio.JavaioFile;
import net.bodz.redist.installer.SessionException;
import net.bodz.redist.installer.TestProject;

public class FileCopyTest
        extends ComponentTestApp {

    public FileCopyTest()
            throws Exception {
        File tmp = TempFile.getTempRoot();
        File localdir = new File(tmp, "localdir");
        localdir.mkdirs();
        File catfile = new File(localdir, "cat");

        new JavaioFile(catfile).tooling()._for(StreamWriting.class).write("a black fat cat. ");

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

package net.bodz.pkg.sis.c.io;

import java.io.File;

import org.junit.Test;

import net.bodz.bas.c.java.io.TempFile;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.pkg.sis.TestSisProject;
import net.bodz.pkg.sis.c.SisComponentTester;
import net.bodz.pkg.sis.c.io.FileCopy;

public class FileCopyTest
        extends SisComponentTester {

    public FileCopyTest()
            throws Exception {
        File tmp = TempFile.getTempRoot();
        File localdir = new File(tmp, "localdir");
        localdir.mkdirs();
        File catfile = new File(localdir, "cat");

        new PojfFile(catfile).to(StreamWriting.class).writeString("a black fat cat. ");
        
        new FileCopy(section, TestSisProject.BASE_A, "copytest", localdir, catfile);
    }

    @Test
    @Override
    public void testPack() {
        super.testPack();
    }

    @Test
    @Override
    public void testInstall() {
        super.testInstall();
    }

    @Test
    @Override
    public void testRemove() {
        super.testRemove();
    }

}

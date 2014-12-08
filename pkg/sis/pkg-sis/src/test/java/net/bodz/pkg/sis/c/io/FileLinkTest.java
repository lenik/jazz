package net.bodz.pkg.sis.c.io;

import org.junit.Test;

import net.bodz.bas.io.res.builtin.ByteArrayResource;
import net.bodz.pkg.sis.TestSisProject;
import net.bodz.pkg.sis.c.SisComponentTester;

public class FileLinkTest
        extends SisComponentTester {

    FileLink link;
    FileLink symlink;

    public FileLinkTest() {
        byte[] data1 = "A big fat black cat".getBytes();

        new CreateFile(section, //
                TestSisProject.BASE_A, "cat", new ByteArrayResource(data1));

        link = new FileLink(section, TestSisProject.BASE_A, "cat", //
                TestSisProject.BASE_B, "hard_cat", false);
        symlink = new FileLink(section, TestSisProject.BASE_A, "cat", //
                TestSisProject.BASE_B, "sym_cat", true);

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

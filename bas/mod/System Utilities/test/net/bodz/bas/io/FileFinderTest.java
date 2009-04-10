package net.bodz.bas.io;

import java.io.File;

import net.bodz.bas.snm.SJProject;

import org.junit.Test;

public class FileFinderTest {

    static File srcDir;
    static {
        srcDir = SJProject.findBase(FileFinderTest.class);
    }

    @Test
    public void test() throws Exception {
        if (srcDir == null) // ignored.
            return;
        FileFinder finder = new FileFinder(srcDir);
        for (File file : finder) {
            System.out.println(file);
        }
    }

}

package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileFilter;

import net.bodz.bas.snm.EclipseProject;

import org.junit.Test;

public class FileFinderTest {

    static File srcDir;
    static {
        srcDir = EclipseProject.getSourceFolder(FileFinderTest.class).path;
    }

    int dump(String title, Iterable<File> files) {
        int count = 0;
        System.out.println(title);
        for (File f : files) {
            count++;
            System.out.println("  " + count + ". " + f); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return count;
    }

    @Test
    public void test1() throws Exception {
        if (srcDir == null) // ignored.
            return;

        int n = dump("normal", new FileFinder(srcDir)); //$NON-NLS-1$

        int n0 = dump("depth=0", new FileFinder(0, srcDir)); //$NON-NLS-1$
        assertEquals(1, n0);

        FileFilter fileOnly = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isDirectory();
            }
        };

        FileFilter dirOnly = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };

        int nFiles = dump("fileOnly", new FileFinder(fileOnly, false, srcDir)); //$NON-NLS-1$
        int nDirs = dump("dirOnly", new FileFinder(dirOnly, false, srcDir)); //$NON-NLS-1$
        assertEquals(n, nFiles + nDirs);
    }

}

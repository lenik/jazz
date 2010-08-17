package net.bodz.bas.fs;

import java.io.File;
import java.io.FileFilter;

import junit.framework.TestCase;
import net.bodz.bas.fs.traverse.FileFinder;

import org.junit.Test;

public class FileFinderTest
        extends TestCase {

    static File srcDir;
    static {
        srcDir = EclipseProject.getSourceFolder(FileFinderTest.class).path;
    }

    int dump(String title, Iterable<File> files) {
        int count = 0;
        System.out.println(title);
        for (File f : files) {
            count++;
            System.out.println("  " + count + ". " + f);
        }
        return count;
    }

    @Test
    public void test1()
            throws Exception {
        if (srcDir == null) // ignored.
            return;

        int n = dump("normal", new FileFinder(srcDir));

        int n0 = dump("depth=0", new FileFinder(0, srcDir));
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

        int nFiles = dump("fileOnly", new FileFinder(fileOnly, false, srcDir));
        int nDirs = dump("dirOnly", new FileFinder(dirOnly, false, srcDir));
        assertEquals(n, nFiles + nDirs);
    }

}

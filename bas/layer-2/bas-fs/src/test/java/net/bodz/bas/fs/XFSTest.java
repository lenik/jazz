package net.bodz.bas.fs;

import java.io.File;

import junit.framework.TestCase;
import net.bodz.bas.fs.path.IPath;

import org.junit.Test;

public class XFSTest
        extends TestCase {

    @Test
    public void testLocalFile()
            throws Exception {
        String pathstr = "c:/boot.ini";
        File javaFile = new File(pathstr);

        IFile file = XFS.resolveFile(pathstr);
        IPath path = file.getPath();
        IFileContainer container = path.getContainer();
        // container.getFileSystem();
    }

}

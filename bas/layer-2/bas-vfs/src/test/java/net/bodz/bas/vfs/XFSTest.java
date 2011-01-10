package net.bodz.bas.vfs;

import java.io.File;

import junit.framework.TestCase;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;

import org.junit.Test;

public class XFSTest
        extends TestCase {

    @Test
    public void testLocalFile()
            throws Exception {
        String pathstr = "c:/boot.ini";
        File javaFile = new File(pathstr);

        IFile file = VFS.resolveFile(pathstr);
        IPath path = file.getPath();
        IVolume container = path.getVolume();
        // container.getFileSystem();
    }

}

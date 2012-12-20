package net.bodz.bas.vfs.impl.pojf;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.resource.builtin.FileResource;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.path.IPath;

public class PojfFileTest
        extends Assert {

    @Test
    public void readFooTxt()
            throws Exception {
        URL url = PojfFileTest.class.getResource("foo.txt");
        IPath _path = VFS.parse(url.toString());
        // NestedURLPath path = (NestedURLPath) _path;
        IFile file = VFS.resolve(_path);

        FileResource resource = (FileResource) file.getResource();
        File rawFile = resource.getFile();
        // System.out.println(rawFile);
        assertTrue(rawFile.exists());

        String contents = file.tooling()._for(StreamReading.class).readString();
        assertEquals("Bar!\n", contents);
    }

}

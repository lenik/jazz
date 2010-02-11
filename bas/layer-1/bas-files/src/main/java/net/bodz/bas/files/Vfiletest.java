package net.bodz.bas.files;

import java.util.Collection;

import org.apache.commons.vfs.FileName;
import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystem;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.provider.AbstractFileSystem;

public class Vfiletest {

    public static void main(String[] args)
            throws Exception {
        final FileObject dir = VFS.getManager().resolveFile("c:/");
        FileSystem fs = new AbstractFileSystem(dir.getName(), dir, null) {

            @Override
            protected FileObject createFile(FileName name)
                    throws Exception {
                return dir.getChild(name.getBaseName());
            }

            @Override
            protected void addCapabilities(Collection caps) {
            }
        };

        FileObject root = fs.getRoot();
        System.out.println(root);
    }

}

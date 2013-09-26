package net.bodz.bas.vfs.impl.mem;

import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.Stdio;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.util.FileTreeFormatter;

public class MemoryFileTest
        extends Assert {

    @Test
    public void createTree()
            throws IOException {
        IFile targetDir = VFS.resolve("mem:test:/createTree/a/b/c/d");
        targetDir.mkdirs();

        IFile root = VFS.resolve("mem:test:/createTree");
        FileTreeFormatter formatter = new FileTreeFormatter();
        formatter.format(Stdio.cout, root);
    }

    @Test
    public void createFiles()
            throws IOException {
        IFile targetDir = VFS.resolve("mem:test:/createFiles/");

        IFile foo = targetDir.resolve("foo");
        foo.to(StreamWriting.class).writeString("Foo Data");

        PosixFileAttributeView view = foo.getAttributeView(PosixFileAttributeView.class);
        Set<PosixFilePermission> permissions = view.readAttributes().permissions();
        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        view.setPermissions(permissions);

        IFile bar = targetDir.resolve("sub/bar");
        bar.to(StreamWriting.class).writeString("Bar is very long.");

        IFile bar_1 = targetDir.resolve("sub/bar.1");
        bar_1.createLink("bar", true);

        IFile foo_1 = targetDir.resolve("sub/foo.1");
        foo_1.createLink("../foo", true);

        IFile root = VFS.resolve("mem:test:/createFiles");
        FileTreeFormatter formatter = new FileTreeFormatter();
        formatter.setShowSize(true);
        formatter.format(Stdio.cout, root);
    }

}

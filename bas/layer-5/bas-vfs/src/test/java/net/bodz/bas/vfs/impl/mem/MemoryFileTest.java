package net.bodz.bas.vfs.impl.mem;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;
import net.bodz.bas.vfs.util.FileTreeDumper;

public class MemoryFileTest
        extends Assert {

    @Test
    public void createTree() {
        IFile targetDir = VFS.resolve("mem:test:/createTree/a/b/c/d");
        targetDir.mkdirs();

        IFile root = VFS.resolve("mem:test:/createTree");
        FileTreeDumper dumper = new FileTreeDumper();
        dumper.dump(Stdio.cout, root);
    }

    @Test
    public void createFiles()
            throws IOException {
        IFile targetDir = VFS.resolve("mem:test:/createFiles/");

        IFile foo = targetDir.resolve("foo");
        foo.autoCreateParents();
        foo.tooling()._for(StreamWriting.class).writeString("Foo Data");

        IFile bar = targetDir.resolve("sub/bar");
        bar.autoCreateParents();
        bar.tooling()._for(StreamWriting.class).writeString("Bar is very long.");

        IFile root = VFS.resolve("mem:test:/createFiles");
        FileTreeDumper dumper = new FileTreeDumper();
        dumper.setShowSize(true);
        dumper.dump(Stdio.cout, root);
    }

}

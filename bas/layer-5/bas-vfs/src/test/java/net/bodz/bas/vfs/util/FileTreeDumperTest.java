package net.bodz.bas.vfs.util;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.sio.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.VFS;

public class FileTreeDumperTest
        extends Assert {

    @Test
    public void testDump1() {
        IFile start = VFS.resolve("/etc");
        FileTreeDumper dumper = new FileTreeDumper();
        dumper.dump(Stdio.cout, start);
    }

}

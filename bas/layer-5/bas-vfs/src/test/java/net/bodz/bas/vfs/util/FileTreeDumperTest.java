package net.bodz.bas.vfs.util;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.m2.MavenProjectOrigin;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.jdk.JdkFile;

public class FileTreeDumperTest
        extends Assert {

    @Test
    public void testDump1() {
        MavenProjectOrigin orig = MavenProjectOrigin.fromClass(FileTreeDumperTest.class);
        IFile start = new JdkFile(orig.getProjectDir());
        FileTreeDumper dumper = new FileTreeDumper();
        dumper.dump(Stdio.cout, start);
    }

}

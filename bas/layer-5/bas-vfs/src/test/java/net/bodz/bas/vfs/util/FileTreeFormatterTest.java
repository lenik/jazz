package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenPom;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.nio.NioFile;

public class FileTreeFormatterTest
        extends Assert {

    public void dumpPomDir()
            throws IOException {
        MavenPom pom = MavenPom.fromClass(FileTreeFormatterTest.class);
        File projectDir = pom.getBaseDir();

        // IFile start = new PojfFile(projectDir);
        IFile start = new NioFile(projectDir);

        FileTreeFormatter formatter = new FileTreeFormatter();
        formatter.format(Stdio.cout, start);
    }

}

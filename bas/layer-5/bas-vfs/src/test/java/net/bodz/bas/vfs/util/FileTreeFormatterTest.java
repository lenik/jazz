package net.bodz.bas.vfs.util;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenPomDir;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.nio.NioFile;

public class FileTreeFormatterTest
        extends Assert {

    public void dumpPomDir()
            throws IOException {
        MavenPomDir pomDir = MavenPomDir.fromClass(FileTreeFormatterTest.class);
        if (pomDir == null)
            throw new NullPointerException("pomDir");

        Path baseDir = pomDir.getBaseDir();

        // IFile start = new PojfFile(projectDir);
        IFile start = new NioFile(baseDir);

        FileTreeFormatter formatter = new FileTreeFormatter();
        formatter.format(Stdio.cout, start);
    }

}

package net.bodz.bas.vfs.util;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;

import net.bodz.bas.c.m2.MavenProjectOrigin;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.impl.nio.NioFile;

public class FileTreeFormatterTest
        extends Assert {

    public void dumpPomDir()
            throws IOException {
        MavenProjectOrigin pom = MavenProjectOrigin.fromClass(FileTreeFormatterTest.class);
        File projectDir = pom.getProjectDir();

        // IFile start = new PojfFile(projectDir);
        IFile start = new NioFile(projectDir);

        FileTreeFormatter formatter = new FileTreeFormatter();
        formatter.format(Stdio.cout, start);
    }

}

package net.bodz.bas.c.m2;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.type.ClassResource;

public class MavenPomDirTest
        extends Assert {

    MavenPomDir pomDir = MavenPomDir.fromClass(getClass());

    @Test
    public void testGetClassFile() {
        Path file = ClassResource.getClassBytesLocalFile(MavenPomDirTest.class);
        assertTrue(Files.exists(file));
    }

    @Test
    public void testGetSourceFile() {
        Path file = pomDir.getSourceFile(MavenPomDirTest.class);
        assertTrue(Files.exists(file));
    }

    @Test
    public void testGetResourceDir() {
        Path file = pomDir.getResourceDir(MavenPomDirTest.class);
        assertTrue(Files.isDirectory(file));
    }

}

package net.bodz.bas.c.m2;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.type.ClassResource;

public class MavenPomDirTest
        extends Assert {

    MavenPomDir pomDir = MavenPomDir.fromClass(getClass());

    @Test
    public void testGetClassFile() {
        File file = ClassResource.getClassBytesFile(MavenPomDirTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetSourceFile() {
        File file = pomDir.getSourceFile(MavenPomDirTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetResourceDir() {
        File file = pomDir.getResourceDir(MavenPomDirTest.class);
        assertTrue(file.isDirectory());
    }

}

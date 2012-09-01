package net.bodz.bas.snm;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.FileClass;

public class MavenProjectTest
        extends Assert {

    MavenProjectOrigin project = MavenProjectOrigin.fromClass(getClass());

    @Test
    public void testGetClassFile() {
        File file = FileClass.getClassFile(MavenProjectTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetSourceFile() {
        File file = project.getSourceFile(MavenProjectTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetResourceDir() {
        File file = project.getResourceDir(MavenProjectTest.class);
        assertTrue(file.isDirectory());
    }

}

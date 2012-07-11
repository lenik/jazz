package net.bodz.bas.c.misc;

import java.io.File;

import net.bodz.bas.c.misc.MavenPath;

import org.junit.Assert;
import org.junit.Test;

public class MavenPathTest
        extends Assert {

    @Test
    public void testGetClassFile() {
        File file = MavenPath.getClassFile(MavenPathTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetSourceFile() {
        File file = MavenPath.getSourceFile(MavenPathTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetResourceDir() {
        File file = MavenPath.getResourceDir(MavenPathTest.class);
        assertTrue(file.isDirectory());
    }

}
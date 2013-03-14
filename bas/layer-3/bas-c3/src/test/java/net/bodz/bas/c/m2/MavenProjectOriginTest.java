package net.bodz.bas.c.m2;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.type.ClassResource;

public class MavenProjectOriginTest
        extends Assert {

    MavenPom project = MavenPom.fromClass(getClass());

    @Test
    public void testGetClassFile() {
        File file = ClassResource.getClassBytesFile(MavenProjectOriginTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetSourceFile() {
        File file = project.getSourceFile(MavenProjectOriginTest.class);
        assertTrue(file.exists());
    }

    @Test
    public void testGetResourceDir() {
        File file = project.getResourceDir(MavenProjectOriginTest.class);
        assertTrue(file.isDirectory());
    }

}

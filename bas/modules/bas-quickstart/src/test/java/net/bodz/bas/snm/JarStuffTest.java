package net.bodz.bas.snm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class JarStuffTest {

    private String magic = "MaGiC-GoOd.."; //$NON-NLS-1$

    File projectBase;

    public JarStuffTest() {
        projectBase = EclipseProject.findProjectBase(JarStuffTest.class);
    }

    @Test
    public void testFindProjectBase() throws Exception {
        System.out.println("[projbase] " + projectBase); //$NON-NLS-1$
    }

    @Test
    public void testGetBaseClasspath() throws Exception {
        File obSelf = JarLocations.getBaseClasspath(JarStuffTest.class);
        assertEquals(new File(projectBase, "basicfx/target/test-classes"), obSelf); //$NON-NLS-1$
        File obRt = JarLocations.getBaseClasspath(Object.class);
        System.out.println("[outbase] Object.class => " + obRt); //$NON-NLS-1$
    }

    @Test
    public void testGetResBase() throws Exception {
        ResFolder rbSelf = JarLocations.getResBase(JarStuffTest.class);
        assertEquals(new FileResFolder(new File(projectBase, "basicfx/target/test-classes")), //$NON-NLS-1$
                rbSelf);
        ResFolder rbRt = JarLocations.getResBase(Object.class);
        System.out.println("[resbase] Object.class => " + rbRt); //$NON-NLS-1$
    }

    @Test
    public void testGetSrcURL_findMagic() throws IOException {
        URL src = BuildPath.getSrcURL(JarStuffTest.class);
        assertNotNull("can't find src", src);
        String code = Files.readAll(src);
        assertEquals(1, Strings.count(code, magic));
    }

    @Test
    public void testGetSrcURL_rtjar() throws IOException {
        String classRes = Files.classData(Object.class).toString();
        String srcRes = BuildPath.getSrcURL(Object.class).toString();
        assertNotNull("can't find src of Object.class", srcRes);
        String srcExpected = classRes.replace(".class", ".java"); //$NON-NLS-1$ //$NON-NLS-2$
        srcExpected = srcExpected.replace("rt.jar!", "rt-src.jar!"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals(srcExpected, srcRes);
    }

    @Test
    public void testGetSrcURLWithName() throws IOException {
        //        URL url = BuildPath.getSrcURLWithName(BasicCLI.class, "a/Option.java"); //$NON-NLS-1$
        //        System.out.println("[src/name] (BasicCLI, a/Option) => " + url); //$NON-NLS-1$
    }

}

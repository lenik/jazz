package net.bodz.bas.snm;

import static org.junit.Assert.assertTrue;

import java.net.URL;

import net.bodz.bas.snm.BuildPath.SourceFolder;

import org.junit.Test;

public class EclipseProjectTest {

    @Test
    public void testGetURLClasspath() throws Exception {
        EclipseProject project = EclipseProject.findFromCWD();
        URL[] cp = project.getURLClasspath();
        // 2*(app, bios, bundled, dtypes, fp, lang, sys, text, wrap)
        assertTrue(cp.length > 10);
        for (URL url : cp)
            System.out.println(url);
    }

    @Test
    public void testGetSourceFolder() {
        SourceFolder testsrc = EclipseProject.getSourceFolder(EclipseProjectTest.class);
        System.out.println("Test src folder: " + testsrc);
        System.out.println("Test src output: " + testsrc.output);
    }

}

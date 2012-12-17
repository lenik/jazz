package net.bodz.bas.snm;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import net.bodz.bas.snm.eclipse.EclipseProject;
import net.bodz.bas.snm.eclipse.BuildPath.SourceFolder;

public class EclipseProjectTest {

    File projectBase;

    public EclipseProjectTest() {
        projectBase = EclipseProject.findProjectBase(getClass());
    }

    @Test
    public void testGetURLClasspath()
            throws Exception {
        if (projectBase == null)
            return;

        EclipseProject project = EclipseProject.findFromCWD();
        URL[] cp = project.getURLClasspath();
        // 2*(app, bios, bundled, dtypes, fp, lang, sys, text, wrap)
        for (URL url : cp)
            System.out.println(url);
    }

    @Test
    public void testGetSourceFolder() {
        if (projectBase == null)
            return;

        SourceFolder testsrc = EclipseProject.getSourceFolder(EclipseProjectTest.class);
        if (testsrc == null) {
            System.out.println("Not an eclipse project");
            return;
        }
        System.out.println("Test src folder: " + testsrc);
        System.out.println("Test src output: " + testsrc.output);
    }

}

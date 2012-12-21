package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.net.URL;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

public class JavaProjectBaseDirTest
        extends Assert {

    File baseDir;
    JavaProject project;

    public JavaProjectBaseDirTest()
            throws Exception {
        baseDir = JavaProjectBaseDir.forClass(getClass());
        if (baseDir != null)
            project = new JavaProject(baseDir);
    }

    @Test
    public void testGetURLClasspath()
            throws Exception {
        Assume.assumeNotNull(project);

        URL[] cp = project.getURLClasspath();
        // 2*(app, bios, bundled, dtypes, fp, lang, sys, text, wrap)
        for (URL url : cp)
            System.out.println(url);
    }

    @Test
    public void testGetSourceFolder()
            throws Exception {
        Assume.assumeNotNull(project);

        JavaProjectSourceFolder testSourceFolder = project.findSourceFolder(JavaProjectBaseDirTest.class);
        if (testSourceFolder == null) {
            System.out.println("Not an eclipse project");
            return;
        }
        System.out.println("Test src folder: " + testSourceFolder);
        System.out.println("Test src output: " + testSourceFolder.output);
    }

}

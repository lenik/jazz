package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JavaProjectTest
        extends Assert {

    JavaProject project;

    @Before
    public void init()
            throws Exception {
        File baseDir = JavaProjectBaseDir.forClass(JavaProject.class);

        System.out.println("Project base: " + baseDir);
        project = new JavaProject(baseDir);

        // Library rt = new Library();
        // rt.path = new File(System.getenv("JAVA_HOME") + "/jre/lib/rt.jar");
        // buildPath.addLibrary(rt);
    }

    public void testType(JavaProject project, Class<?> clazz, String expectedTargetPath, String expectedSourcePath)
            throws Exception {
        String targetPath = null;
        String sourcePath = null;

        if (project == null) {
            File baseDir = JavaProjectBaseDir.forClass(clazz);
            if (baseDir != null)
                project = new JavaProject(baseDir);
        }

        if (project == null) {
            assertNull(expectedTargetPath);
            assertNull(expectedSourcePath);
            return;
        }

        List<JavaProjectSourceFolder> srcdirs = project.getSourceFolders();
        assertFalse(srcdirs.isEmpty());

        targetPath = project.getDefaultOutput().toString();
        sourcePath = srcdirs.get(0).toString();

        System.out.println("Class: " + clazz);

        JavaProjectBuildPathEntry entry = project.findBuildPathEntry(clazz);
        System.out.println("    class: " + entry);

        URL sourceURL = project.findSourceURL(clazz);
        System.out.println("    source: " + sourceURL);
        System.out.println();
    }

    @Test
    public void testProjectType()
            throws Exception {
        testType(project, JavaProject.class, null, null);
    }

}

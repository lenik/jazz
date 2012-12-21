package net.bodz.bas.c.org.eclipse;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class JavaProjectTest {

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

    public void testType(JavaProject project, Class<?> clazz, String classPath, String sourcePath)
            throws Exception {
        if (project == null) {
            File baseDir = JavaProjectBaseDir.forClass(clazz);
            project = new JavaProject(baseDir);
        }

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

    @Test
    public void testSysLib()
            throws Exception {
        testType(null, Pattern.class, null, null);
        testType(null, Map.Entry.class, null, null);
    }

}

package net.bodz.bas.snm;

import java.io.File;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

import net.bodz.bas.snm.eclipse.BuildPath;
import net.bodz.bas.snm.eclipse.EclipseProject;
import net.bodz.bas.snm.eclipse.BuildPath.ClassContainer;
import net.bodz.bas.snm.eclipse.BuildPath.SourceResource;

public class BuildPathTest {

    static BuildPath projectBuildPath;

    @BeforeClass
    public static void init()
            throws Exception {
        File projectBase = EclipseProject.findProjectBase(BuildPath.class);
        System.out.println("Project base: " + projectBase);
        EclipseProject project = new EclipseProject(projectBase);
        projectBuildPath = project.getBuildPath();
        // Library rt = new Library();
        // rt.path = new File(System.getenv("JAVA_HOME") + "/jre/lib/rt.jar");
        // buildPath.addLibrary(rt);
    }

    public void testType(BuildPath buildPath, Class<?> type, String classPath, String sourcePath)
            throws Exception {
        if (buildPath == null)
            buildPath = new BuildPath(type);
        System.out.println("Class: " + type);
        ClassContainer cc = buildPath.whichClassContainer(type);
        System.out.println("    class: " + cc);
        SourceResource sr = buildPath.findSourceResource(type);
        System.out.println("    source: " + sr);
        System.out.println();
    }

    @Test
    public void testProjectType()
            throws Exception {
        testType(projectBuildPath, BuildPath.class, null, null);
    }

    @Test
    public void testSysLib()
            throws Exception {
        testType(null, Pattern.class, null, null);
        testType(null, Map.Entry.class, null, null);
    }

}

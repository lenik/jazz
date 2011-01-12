package net.bodz.bas.ant;

import java.io.File;
import java.util.Map;

import net.bodz.bas.snm.EclipseProject;
import net.bodz.bas.util.exception.ParseException;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

/**
 * @test {@link ProjectInfoTest}
 */
public class ProjectInfo {

    private Project antProject;

    private File projectBase;
    private Path classpath;
    private Path classpathWithTest;

    private Map<String, Module> modules;

    public static class Module {

        public FileSet srcFiles;
        public FileSet binFiles;

    }

    public ProjectInfo(Project antProject)
            throws ParseException {
        this(antProject, CWD.getcwd());
    }

    public ProjectInfo(Project antProject, File searchStart)
            throws ParseException {
        if (antProject == null)
            throw new NullPointerException("project");
        this.antProject = antProject;

        projectBase = EclipseProject.findProjectBase(searchStart);
        if (projectBase == null)
            throw new RuntimeException("Can\'t find the eclipse project, search from " + searchStart);

        parse();
    }

    /**
     * Convert classpath to ant.Path objects.
     */
    void parse()
            throws ParseException {
        EclipseProject eproj = new EclipseProject(projectBase);
        classpath = new Path(antProject);
        classpathWithTest = new Path(antProject);
        for (File f : eproj.getFileClasspath()) {
            boolean nonTest = !f.getName().contains("test"); // test.bin
            Path path = new Path(antProject, f.getPath());
            if (nonTest)
                classpath.add(path);
            classpathWithTest.add(path);
        }
    }

    public File getBase() {
        return projectBase;
    }

    public Path getClasspath() {
        return classpath;
    }

    public Path getClasspathWithTest() {
        return classpathWithTest;
    }

    public Map<String, Module> getModules() {
        return modules;
    }

}

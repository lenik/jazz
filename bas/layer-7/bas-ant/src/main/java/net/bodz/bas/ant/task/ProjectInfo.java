package net.bodz.bas.ant.task;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

import net.bodz.bas.c.org.eclipse.JavaProject;
import net.bodz.bas.c.org.eclipse.JavaProjectBaseDir;
import net.bodz.bas.c.system.UserDirScr;
import net.bodz.bas.err.ParseException;

public class ProjectInfo {

    private Project antProject;

    private File baseDir;
    private Path classpath;
    private Path classpathWithTest;

    private Map<String, Module> modules;

    public static class Module {

        public FileSet srcFiles;
        public FileSet binFiles;

    }

    public ProjectInfo(Project antProject)
            throws ParseException, IOException {
        this(antProject, UserDirScr.getInstance().get());
    }

    public ProjectInfo(Project antProject, File searchStart)
            throws ParseException, IOException {
        if (antProject == null)
            throw new NullPointerException("project");
        this.antProject = antProject;

        baseDir = JavaProjectBaseDir.findParents(searchStart);
        if (baseDir == null)
            throw new RuntimeException("Can\'t find the eclipse project, search from " + searchStart);

        parse();
    }

    /**
     * Convert classpath to ant.Path objects.
     */
    void parse()
            throws ParseException, IOException {
        JavaProject project = new JavaProject(baseDir);
        classpath = new Path(antProject);
        classpathWithTest = new Path(antProject);
        for (File f : project.getClasspath()) {
            boolean nonTest = !f.getName().contains("test"); // test.bin
            Path path = new Path(antProject, f.getPath());
            if (nonTest)
                classpath.add(path);
            classpathWithTest.add(path);
        }
    }

    public File getBase() {
        return baseDir;
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

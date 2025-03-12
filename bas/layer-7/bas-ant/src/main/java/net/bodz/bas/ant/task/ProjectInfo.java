package net.bodz.bas.ant.task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

import net.bodz.bas.c.java.nio.file.PathFn;
import net.bodz.bas.c.org.eclipse.JavaProject;
import net.bodz.bas.c.org.eclipse.JavaProjectBaseDir;
import net.bodz.bas.ctx.sys.UserDirVars;
import net.bodz.bas.err.ParseException;

public class ProjectInfo {

    private Project antProject;

    private Path baseDir;
    private AntPath classpath;
    private AntPath classpathWithTest;

    private Map<String, Module> modules;

    public static class Module {

        public FileSet srcFiles;
        public FileSet binFiles;

    }

    public ProjectInfo(Project antProject)
            throws ParseException, IOException {
        this(antProject, UserDirVars.getInstance().get());
    }

    public ProjectInfo(Project antProject, Path searchStart)
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
        classpath = new AntPath(antProject);
        classpathWithTest = new AntPath(antProject);
        for (Path f : project.getClasspath()) {
            boolean nonTest = !PathFn.baseName(f).contains("test"); // test.bin
            AntPath antPath = new AntPath(antProject, f.toString());
            if (nonTest)
                classpath.add(antPath);
            classpathWithTest.add(antPath);
        }
    }

    public Path getBase() {
        return baseDir;
    }

    public AntPath getClasspath() {
        return classpath;
    }

    public AntPath getClasspathWithTest() {
        return classpathWithTest;
    }

    public Map<String, Module> getModules() {
        return modules;
    }

}

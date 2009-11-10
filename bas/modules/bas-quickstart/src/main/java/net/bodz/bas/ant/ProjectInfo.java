package net.bodz.bas.ant;

import java.io.File;

import net.bodz.bas.io.CWD;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.snm.EclipseProject;
import net.bodz.bas.types.TextMap;

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

    private TextMap<Module> modules;

    public static class Module {

        public FileSet srcFiles;
        public FileSet binFiles;

    }

    public ProjectInfo(Project antProject) throws ParseException {
        this(antProject, CWD.getcwd());
    }

    public ProjectInfo(Project antProject, File searchStart) throws ParseException {
        if (antProject == null)
            throw new NullPointerException("project"); //$NON-NLS-1$
        this.antProject = antProject;

        projectBase = EclipseProject.findProjectBase(searchStart);
        if (projectBase == null)
            throw new RuntimeException(AppNLS.getString("ProjectInfo.cantFindEclipseProject") + searchStart); //$NON-NLS-1$

        parse();
    }

    /**
     * Convert classpath to ant.Path objects.
     */
    void parse() throws ParseException {
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

    public TextMap<Module> getModules() {
        return modules;
    }

}

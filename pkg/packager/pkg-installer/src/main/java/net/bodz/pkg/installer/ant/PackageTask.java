package net.bodz.pkg.installer.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import net.bodz.bas.ant.logger.TaskLogger;
import net.bodz.bas.err.ExceptionBuffer;
import net.bodz.bas.err.RecoverableExceptionEvent;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.impl.pojf.PojfFile;
import net.bodz.pkg.installer.ConsoleExecutor;
import net.bodz.pkg.installer.IProject;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.SessionException;

/**
 * Ant task for package Distins-Project
 */
public class PackageTask
        extends Task
        implements II18nCapable {

    private IProject project;
    private IFsDir output;
    private int verboseLevel;

    public PackageTask() {
    }

    public void setProjectClass(String projectClassName)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (IProject) projectClass.newInstance();
    }

    public void setResFolder(IFsDir resFolder) {
        if (this.output != null)
            throw new BuildException(tr._("Output is already specified: ") + output);
        this.output = resFolder;
    }

    public void setOutDir(File outdir) {
        IFile folder = new PojfFile(outdir);
        setResFolder(folder);
    }

    public void setOutJar(File zipFile) {
        IFile outjar = new ZipResFolder(zipFile);
        setResFolder(outjar);
    }

    public int getVerboseLevel() {
        return verboseLevel;
    }

    public void setVerboseLevel(int verboseLevel) {
        this.verboseLevel = verboseLevel;
    }

    @Override
    public void execute()
            throws BuildException {
        if (project == null)
            throw new BuildException(tr._("Project isn\'t specified"));
        if (output == null)
            throw new BuildException(tr._("Resource folder isn\'t specified"));
        TaskLogger logger = new TaskLogger(this);
        logger.setLevel(logger.getLevel(), verboseLevel);
        final List<Exception> exceptions = new ArrayList<Exception>();
        ConsoleExecutor executor = new ConsoleExecutor(project, logger) {
            @Override
            public void exceptionThrown(Exception e) {
                exceptions.add(e);
            }

            @Override
            public void recoverException(RecoverableExceptionEvent e) {
                exceptions.add(e.getException());
            }
        };
        ISession session = executor.getSession();
        session.addResFolder(0, output);
        try {
            executor.pack();
        } catch (SessionException e) {
            throw new BuildException(e);
        }
        if (!exceptions.isEmpty()) {
            String summary = ExceptionBuffer.summary(exceptions);
            throw new BuildException(summary, exceptions.get(0));
        }
    }

}

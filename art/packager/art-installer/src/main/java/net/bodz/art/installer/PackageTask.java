package net.bodz.art.installer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.ant.TaskLogger;
import net.bodz.bas.util.exception.ExceptionBuffer;
import net.bodz.bas.util.exception.RecoverableExceptionEvent;
import net.bodz.bas.vfs.IFsTree;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Ant task for package Distins-Project
 */
public class PackageTask
        extends Task {

    private IProject project;
    private IFsTree output;
    private int logLevel;

    public PackageTask() {
    }

    public void setProjectClass(String projectClassName)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (IProject) projectClass.newInstance();
    }

    public void setResFolder(IFsTree resFolder) {
        if (this.output != null)
            throw new BuildException(PackNLS.getString("PackageTask.outputIsSpecified") + output);
        this.output = resFolder;
    }

    public void setOutDir(File outdir) {
        FileResFolder folder = new FileResFolder(outdir, true);
        setResFolder(folder);
    }

    public void setOutJar(File zipFile) {
        ZipResFolder outjar = new ZipResFolder(zipFile, true);
        setResFolder(outjar);
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void execute()
            throws BuildException {
        if (project == null)
            throw new BuildException(PackNLS.getString("PackageTask.projectIsntSpecified"));
        if (output == null)
            throw new BuildException(PackNLS.getString("PackageTask.resFolderIsntSpecified"));
        TaskLogger logger = new TaskLogger(this);
        logger.setLevel(logger.getLevel() + logLevel);
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

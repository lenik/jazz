package net.bodz.dist.ins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.ant.TaskLogTerm;
import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ZipResFolder;
import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.xml.ExceptionBuffer;
import net.bodz.dist.ins.nls.PackNLS;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Ant task for package Distins-Project
 * 
 * @test {@link PackageTaskTest}
 */
public class PackageTask extends Task {

    private Project   project;
    private ResFolder output;
    private int       logLevel;

    public PackageTask() {
    }

    public void setProjectClass(String projectClassName) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (Project) projectClass.newInstance();
    }

    public void setResFolder(ResFolder resFolder) {
        if (this.output != null)
            throw new BuildException(PackNLS.getString("PackageTask.outputIsSpecified") + output); //$NON-NLS-1$
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
    public void execute() throws BuildException {
        if (project == null)
            throw new BuildException(PackNLS.getString("PackageTask.projectIsntSpecified")); //$NON-NLS-1$
        if (output == null)
            throw new BuildException(PackNLS.getString("PackageTask.resFolderIsntSpecified")); //$NON-NLS-1$
        TaskLogTerm logger = new TaskLogTerm(this);
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

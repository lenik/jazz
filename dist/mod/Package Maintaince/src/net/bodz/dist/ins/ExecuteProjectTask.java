package net.bodz.dist.ins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.bodz.bas.ant.TaskLogTerm;
import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ZipResFolder;
import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TreeTextMap;
import net.bodz.bas.xml.ExceptionBuffer;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public abstract class ExecuteProjectTask extends Task {

    private Project       project;
    private String        scheme;
    private ResFolder     resFolder;
    private TextMap<File> baseDirs;
    private int           logLevel;

    public ExecuteProjectTask() {
        baseDirs = new TreeTextMap<File>();
    }

    public void setProjectClass(String projectClassName)
            throws ClassNotFoundException, InstantiationException,
            IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (Project) projectClass.newInstance();
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setResFolder(ResFolder resFolder) {
        if (this.resFolder != null)
            throw new BuildException("ResFolder is already specified: "
                    + resFolder);
        this.resFolder = resFolder;
    }

    public void setPackDir(File outdir) {
        FileResFolder folder = new FileResFolder(outdir);
        folder.setAutoMkdirs(false);
        setResFolder(folder);
    }

    public void setPackJar(File zipFile) {
        ZipResFolder outjar = new ZipResFolder(zipFile);
        outjar.setAutoMkdirs(false);
        setResFolder(outjar);
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void addConfiguredBaseDir(BaseDirElement e) {
        if (e == null)
            throw new NullPointerException("e");
        if (e.name == null)
            throw new IllegalArgumentException(
                    "Name of the base dir isn't specified");
        if (e.location == null)
            throw new IllegalArgumentException(
                    "Location of the base dir isn't specified");
        if (baseDirs.containsKey(e.name))
            throw new IllegalArgumentException("Base dir " + e.name
                    + " is already defined");
        baseDirs.put(e.name, e.location);
    }

    protected abstract int getType();

    @Override
    public void execute() throws BuildException {
        if (project == null)
            throw new BuildException("Project isn't specified");
        if (resFolder == null)
            throw new BuildException("Resource folder isn't specified");
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
        if (scheme != null)
            session.setScheme(scheme);
        session.setResFolder(resFolder);
        for (Entry<String, File> e : baseDirs.entrySet()) {
            session.setBaseDir(e.getKey(), e.getValue());
        }
        try {
            switch (getType()) {
            case Component.INSTALL:
                executor.install();
                break;
            case Component.UNINSTALL:
                executor.uninstall();
                break;
            default:
                throw new UnexpectedException();
            }
        } catch (SessionException e) {
            throw new BuildException(e);
        }
        if (!exceptions.isEmpty()) {
            String summary = ExceptionBuffer.summary(exceptions);
            throw new BuildException(summary, exceptions.get(0));
        }
    }

}

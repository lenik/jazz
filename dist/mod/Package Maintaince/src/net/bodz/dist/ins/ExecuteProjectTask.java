package net.bodz.dist.ins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.ant.NamedParameter;
import net.bodz.bas.ant.TaskLogTerm;
import net.bodz.bas.ant.WithNamedParameters;
import net.bodz.bas.io.FileResFolder;
import net.bodz.bas.io.ResFolder;
import net.bodz.bas.io.ZipResFolder;
import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.xml.ExceptionBuffer;
import net.bodz.dist.nls.PackNLS;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public abstract class ExecuteProjectTask extends Task {

    private Project             project;
    private String              scheme;
    private ResFolder           resFolder;
    private WithNamedParameters parameters;
    private int                 logLevel;

    public ExecuteProjectTask() {
        parameters = new WithNamedParameters();
    }

    public void setProjectClass(String projectClassName) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (Project) projectClass.newInstance();
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setResFolder(ResFolder resFolder) {
        if (this.resFolder != null)
            throw new BuildException(PackNLS.getString("ExecuteProjectTask.resFolderIsSpecified") + resFolder); //$NON-NLS-1$
        this.resFolder = resFolder;
    }

    public void setPackDir(File outdir) {
        FileResFolder src = new FileResFolder(outdir);
        setResFolder(src);
    }

    public void setPackJar(File zipFile) {
        ZipResFolder src = new ZipResFolder(zipFile);
        setResFolder(src);
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void addConfiguredParameter(NamedParameter parameter) throws ParseException {
        parameters.addConfiguredParameter(parameter);
    }

    protected abstract int getType();

    @Override
    public void execute() throws BuildException {
        if (project == null)
            throw new BuildException(PackNLS.getString("ExecuteProjectTask.projectIsntSpecified")); //$NON-NLS-1$
        if (resFolder == null)
            throw new BuildException(PackNLS.getString("ExecuteProjectTask.resFolderIsntSpecified")); //$NON-NLS-1$
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
        session.addResFolder(resFolder);

        Map<String, Variable> vardef = project.getVariables();
        TextMap<Object> map = parameters.getMap();
        for (Map.Entry<String, Object> e : map.entrySet()) {
            String name = e.getKey();
            if (!vardef.containsKey(name))
                throw new IllegalArgumentException(PackNLS.getString("ExecuteProjectTask.undefinedVariable") + name); //$NON-NLS-1$
            Object value = e.getValue();
            session.set(name, value);
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

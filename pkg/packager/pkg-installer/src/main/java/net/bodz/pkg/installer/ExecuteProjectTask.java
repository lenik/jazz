package net.bodz.pkg.installer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import net.bodz.bas.ant.logger.TaskLogger;
import net.bodz.bas.ant.util.NamedParameter;
import net.bodz.bas.ant.util.WithNamedParameters;
import net.bodz.bas.err.ExceptionBuffer;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.RecoverableExceptionEvent;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.vfs.IFile;
import net.bodz.bas.vfs.IFsDir;
import net.bodz.bas.vfs.impl.pojf.PojfFile;

public abstract class ExecuteProjectTask
        extends Task
        implements II18nCapable {

    private IProject project;
    private String scheme;
    private IFsDir resFolder;
    private WithNamedParameters parameters;
    private int verboseLevel;

    public ExecuteProjectTask() {
        parameters = new WithNamedParameters();
    }

    public void setProjectClass(String projectClassName)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> projectClass = Class.forName(projectClassName);
        project = (IProject) projectClass.newInstance();
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setResFolder(IFsDir resFolder) {
        if (this.resFolder != null)
            throw new BuildException(tr._("ResFolder is already specified: ") + resFolder);
        this.resFolder = resFolder;
    }

    public void setPackDir(File outdir) {
        PojfFile src = new PojfFile(outdir);
        setResFolder(src);
    }

    public void setPackJar(File zipFile) {
        IFile src = new PojfFile(zipFile);
        setResFolder(src);
    }

    public int getVerboseLevel() {
        return verboseLevel;
    }

    public void setVerboseLevel(int verboseLevel) {
        this.verboseLevel = verboseLevel;
    }

    public void addConfiguredParameter(NamedParameter parameter)
            throws ParseException {
        parameters.addConfiguredParameter(parameter);
    }

    protected abstract int getType();

    @Override
    public void execute()
            throws BuildException {
        if (project == null)
            throw new BuildException(tr._("Project isn\'t specified"));
        if (resFolder == null)
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
        if (scheme != null)
            session.setScheme(scheme);
        session.addResFolder(resFolder);

        Map<String, Variable> vardef = project.getVariables();
        Map<String, ?> map = parameters.getMap();
        for (Map.Entry<String, ?> e : map.entrySet()) {
            String name = e.getKey();
            if (!vardef.containsKey(name))
                throw new IllegalArgumentException(tr._("Undefined variable: ") + name);
            Object value = e.getValue();
            session.set(name, value);
        }

        try {
            switch (getType()) {
            case IComponent.INSTALL:
                executor.install();
                break;
            case IComponent.UNINSTALL:
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

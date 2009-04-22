package net.bodz.dist.ins;

import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.JobAdaptor;
import net.bodz.bas.util.LogTerm;

public abstract class ProjectExecutor extends JobAdaptor {

    protected final Project       project;
    protected final ISession      session;

    protected final UserInterface UI;
    protected final LogTerm       L;

    public ProjectExecutor(Project project, UserInterface userInterface,
            LogTerm logger) {
        if (project == null)
            throw new NullPointerException("project");
        if (userInterface == null)
            throw new NullPointerException("userInterface");
        if (userInterface == null)
            throw new NullPointerException("userInterface");
        if (logger == null)
            throw new NullPointerException("logger");
        this.project = project;
        this.session = new Session(project, userInterface, logger);
        this.UI = userInterface;
        this.L = logger;
    }

    public ProjectExecutor(ISession session, UserInterface userInterface,
            LogTerm logger) {
        if (session == null)
            throw new NullPointerException("session");
        this.project = session.getProject();
        this.session = session;
        this.UI = userInterface;
        this.L = logger;
    }

    public Project getProject() {
        return project;
    }

    public ISession getSession() {
        return session;
    }

    public void pack() throws SessionException {
        execute(Component.PACK, session);
        session.closeAttachments();
        session.saveRegistry();
    }

    public void install() throws SessionException {
        session.loadRegistry();
        execute(Component.INSTALL, session);
    }

    public void uninstall() throws SessionException {
        session.loadRegistry();
        execute(Component.UNINSTALL, session);
    }

    void execute(int type, ISession session) throws SessionException {
        SessionJob job = project.execute(type, session);
        bind(job);
        try {
            job.run();
        } finally {
            unbind(job);
        }
    }

}

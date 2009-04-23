package net.bodz.dist.ins;

import net.bodz.bas.io.CharOuts;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.JobObserver;
import net.bodz.bas.util.LogTerm;

public abstract class ProjectExecutor extends JobObserver {

    protected final Project       project;
    protected final ISession      session;

    protected final UserInterface UI;
    protected final LogTerm       L;

    public ProjectExecutor(Project project, UserInterface userInterface, LogTerm logger) {
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

    public ProjectExecutor(ISession session, UserInterface userInterface, LogTerm logger) {
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
        session.closeAttachments();
    }

    public void uninstall() throws SessionException {
        session.loadRegistry();
        execute(Component.UNINSTALL, session);
        session.closeAttachments();
    }

    void execute(int type, ISession session) throws SessionException {
        LogTerm _logger = session.getLogger();
        try {
            session.setLogger(L);
            SessionJob job = project.execute(type, session);
            if (job == null)
                return;
            if (L.showDebug())
                job.dump(CharOuts.stdout);
            bind(job);
            try {
                job.run();
            } finally {
                unbind(job);
            }
        } finally {
            session.setLogger(_logger);
        }
    }

}

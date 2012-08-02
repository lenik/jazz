package net.bodz.art.installer;

import net.bodz.bas.log.api.Logger;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.JobObserver;

public abstract class ProjectExecutor
        extends JobObserver {

    protected final IProject project;
    protected final ISession session;

    protected final UserInterface UI;
    protected final Logger logger;

    public ProjectExecutor(IProject project, UserInterface userInterface, Logger logger) {
        if (project == null)
            throw new NullPointerException("project");
        if (userInterface == null)
            throw new NullPointerException("userInterface");
        if (logger == null)
            throw new NullPointerException("logger");
        this.project = project;
        this.session = new Session(project, userInterface, logger);
        this.UI = userInterface;
        this.logger = logger;
    }

    public ProjectExecutor(ISession session, UserInterface userInterface, Logger logger) {
        if (session == null)
            throw new NullPointerException("session");
        this.project = session.getProject();
        this.session = session;
        this.UI = userInterface;
        this.logger = logger;
    }

    public IProject getProject() {
        return project;
    }

    public ISession getSession() {
        return session;
    }

    public void pack()
            throws SessionException {
        execute(IComponent.PACK, session);
        session.closeAttachments();
        session.saveRegistry();
    }

    public void install()
            throws SessionException {
        session.loadRegistry();
        execute(IComponent.INSTALL, session);
        session.closeAttachments();
    }

    public void uninstall()
            throws SessionException {
        session.loadRegistry();
        execute(IComponent.UNINSTALL, session);
        session.closeAttachments();
    }

    void execute(int type, ISession session)
            throws SessionException {
        Logger _logger = session.getLogger();
        SessionJob job;
        try {
            session.setLogger(logger);
            job = project.execute(type, session);
        } finally {
            session.setLogger(_logger);
        }
        if (job != null) {
            if (logger.isDebugEnabled())
                job.dump(Stdio.cout);
            bind(job);
            try {
                job.run();
            } finally {
                unbind(job);
            }
        }
    }

}

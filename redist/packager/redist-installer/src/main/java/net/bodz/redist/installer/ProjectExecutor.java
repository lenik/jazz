package net.bodz.redist.installer;

import net.bodz.bas.exec.job.JobObserver;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.log.Logger;

public abstract class ProjectExecutor
        extends JobObserver
        implements II18nCapable {

    protected final IProject project;
    protected final ISession session;

    protected final IUserDialogs userDialogs;
    protected final Logger logger;

    public ProjectExecutor(IProject project, IUserDialogs userDialogs, Logger logger) {
        if (project == null)
            throw new NullPointerException("project");
        if (userDialogs == null)
            throw new NullPointerException("userDialogs");
        if (logger == null)
            throw new NullPointerException("logger");
        this.project = project;
        this.session = new Session(project, userDialogs, logger);
        this.userDialogs = userDialogs;
        this.logger = logger;
    }

    public ProjectExecutor(ISession session, IUserDialogs userInterface, Logger logger) {
        if (session == null)
            throw new NullPointerException("session");
        this.project = session.getProject();
        this.session = session;
        this.userDialogs = userInterface;
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

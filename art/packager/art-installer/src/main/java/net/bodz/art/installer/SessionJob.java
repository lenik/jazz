package net.bodz.art.installer;

import java.util.List;

import net.bodz.bas.c.reflect.Reflects;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util.IJob;
import net.bodz.bas.util.Job;

public abstract class SessionJob
        extends Job {

    protected final ISession session;
    private final IComponent component;

    public double progressIncrement;

    public SessionJob(ISession session, IComponent component) {
        if (session == null)
            throw new NullPointerException("session"); //$NON-NLS-1$
        if (component == null)
            throw new NullPointerException("component"); //$NON-NLS-1$
        this.session = session;
        this.component = component;
        setUserInterface(session.getUserInterface());
        setLogger(session.getLogger());
    }

    public ISession getSession() {
        return session;
    }

    public IComponent getComponent() {
        return component;
    }

    @Override
    protected void execute(IJob child, double progressIncrement) {
        if (!(child instanceof SessionJob))
            throw new OutOfDomainException("child", child, SessionJob.class); //$NON-NLS-1$
        super.execute(child, progressIncrement);
    }

    @Override
    public void addChildJob(Job job, double progressIncrement) {
        if (!(job instanceof SessionJob))
            throw new OutOfDomainException("job", job, SessionJob.class); //$NON-NLS-1$
        super.addChildJob(job, progressIncrement);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends SessionJob> getChildren() {
        return (List<? extends SessionJob>) super.getChildren();
    }

    /**
     * install/uninstall process should check this flag frequently to cancel immediately.
     */
    // boolean isCanceling();
    //
    // void cancel();
    //
    // void addCancelListener(CancelListener listener);
    //
    // void removeCancelListener(CancelListener listener);

    public String getDescription() {
        String jobTypeName = Reflects.getNamedSuperclass(getClass()).getSimpleName();
        return String.format("%s for %s (%s)", jobTypeName, component.getText(), component.getId()); //$NON-NLS-1$
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public void dump(IPrintOut out) {
        dump(out, "", this); //$NON-NLS-1$
    }

    public static void dump(IPrintOut out, String prefix, SessionJob job) {
        String s = prefix;
        s += String.format("+%.2f ", job.progressIncrement); //$NON-NLS-1$
        s += job.toString();
        out.println(s);
        List<? extends SessionJob> children = job.getChildren();
        if (children != null) {
            for (SessionJob child : children)
                dump(out, prefix + "  ", child); //$NON-NLS-1$
        }
    }

}

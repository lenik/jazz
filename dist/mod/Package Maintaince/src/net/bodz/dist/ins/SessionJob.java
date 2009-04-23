package net.bodz.dist.ins;

import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.util.Job;
import net.bodz.bas.util._Job;

public abstract class SessionJob extends _Job {

    protected final ISession session;
    private final Component  component;

    public double            progressIncrement;

    public SessionJob(ISession session, Component component) {
        if (session == null)
            throw new NullPointerException("session");
        if (component == null)
            throw new NullPointerException("component");
        this.session = session;
        this.component = component;
        setUserInterface(session.getUserInterface());
        setLogger(session.getLogger());
    }

    public ISession getSession() {
        return session;
    }

    public Component getComponent() {
        return component;
    }

    @Override
    protected void _run() {
    }

    @Override
    protected void execute(Job child, double progressIncrement) {
        if (!(child instanceof SessionJob))
            throw new OutOfDomainException("child", child, SessionJob.class);
        super.execute(child, progressIncrement);
    }

    @Override
    public void addChildJob(_Job job, double progressIncrement) {
        if (!(job instanceof SessionJob))
            throw new OutOfDomainException("job", job, SessionJob.class);
        super.addChildJob(job, progressIncrement);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<? extends SessionJob> getChildren() {
        return (List<? extends SessionJob>) super.getChildren();
    }

    /**
     * install/uninstall process should check this flag frequently to cancel
     * immediately.
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
        return String.format("%s for %s (%s)", jobTypeName, component.getText(), component.getId());
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public void dump(CharOut out) {
        dump(out, "", this);
    }

    public static void dump(CharOut out, String prefix, SessionJob job) {
        String s = prefix;
        s += String.format("%.2f%% ", job.progressIncrement * 100);
        s += job.toString();
        out.println(s);
        List<? extends SessionJob> children = job.getChildren();
        if (children != null) {
            for (SessionJob child : children)
                dump(out, prefix + "  ", child);
        }
    }

}

package net.bodz.dist.ins;

import net.bodz.bas.util._Job;

public abstract class SessionJob extends _Job {

    protected final ISession session;
    private double           childIncrement;

    public SessionJob(ISession session) {
        if (session == null)
            throw new NullPointerException("session");
        this.session = session;
        setUserInterface(session.getUserInterface());
        setLogger(session.getLogger());
    }

    public ISession getSession() {
        return session;
    }

    protected double getChildIncrement() {
        return childIncrement;
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

}

package net.bodz.bas.exec.job;

import net.bodz.bas.err.IRecoverableExceptionListener;

public abstract class JobObserver
        implements IRecoverableExceptionListener, IStatusChangeListener, IProgressChangeListener,
        IDurationChangeListener {

    public void bind(IJob job) {
        if (job == null)
            throw new NullPointerException("job");
        Trace.link(this, "observer", job);
        job.addExceptionListener(this);
        job.addStatusChangeListener(this);
        job.addProgressChangeListener(this);
        job.addDurationChangeListener(this);
    }

    public void unbind(IJob job) {
        if (job == null)
            throw new NullPointerException("job");
        Trace.unlink(this, "observer", job);
        job.removeExceptionListener(this);
        job.removeStatusChangeListener(this);
        job.removeProgressChangeListener(this);
        job.removeDurationChangeListener(this);
    }

}

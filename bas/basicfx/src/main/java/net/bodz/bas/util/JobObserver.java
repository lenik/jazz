package net.bodz.bas.util;

import net.bodz.bas.lang.RecoverableExceptionListener;

public abstract class JobObserver implements RecoverableExceptionListener, StatusChangeListener,
        ProgressChangeListener, DurationChangeListener {

    public void bind(Job job) {
        if (job == null)
            throw new NullPointerException("job"); //$NON-NLS-1$
        Trace.link(this, "observer", job); //$NON-NLS-1$
        job.addExceptionListener(this);
        job.addStatusChangeListener(this);
        job.addProgressChangeListener(this);
        job.addDurationChangeListener(this);
    }

    public void unbind(Job job) {
        if (job == null)
            throw new NullPointerException("job"); //$NON-NLS-1$
        Trace.unlink(this, "observer", job); //$NON-NLS-1$
        job.removeExceptionListener(this);
        job.removeStatusChangeListener(this);
        job.removeProgressChangeListener(this);
        job.removeDurationChangeListener(this);
    }

}

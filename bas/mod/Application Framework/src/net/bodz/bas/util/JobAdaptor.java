package net.bodz.bas.util;

import net.bodz.bas.lang.RecoverableExceptionListener;

public abstract class JobAdaptor implements RecoverableExceptionListener,
        StatusChangeListener, ProgressChangeListener, DurationChangeListener {

    protected void bind(Job job) {
        if (job == null)
            throw new NullPointerException("job");
        job.addExceptionListener(this);
        job.addStatusChangeListener(this);
        job.addProgressChangeListener(this);
        job.addDurationChangeListener(this);
    }

    protected void unbind(Job job) {
        if (job == null)
            throw new NullPointerException("job");
        job.removeExceptionListener(this);
        job.removeStatusChangeListener(this);
        job.removeProgressChangeListener(this);
        job.removeDurationChangeListener(this);
    }

}

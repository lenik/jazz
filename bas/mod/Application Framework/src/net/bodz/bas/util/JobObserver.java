package net.bodz.bas.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.RecoverableExceptionListener;
import net.bodz.bas.types.util.Strings;

public abstract class JobObserver implements RecoverableExceptionListener, StatusChangeListener,
        ProgressChangeListener, DurationChangeListener {

    // #ifdef _DEBUG

    private List<Job> boundJobs = new ArrayList<Job>();

    @Override
    public String toString() {
        return "Observer " + getClass().getSimpleName() + " for " + Strings.join(", ", boundJobs);
    }

    // #endif

    protected void bind(Job job) {
        if (job == null)
            throw new NullPointerException("job");
        boundJobs.add(job);
        job.addExceptionListener(this);
        job.addStatusChangeListener(this);
        job.addProgressChangeListener(this);
        job.addDurationChangeListener(this);
    }

    protected void unbind(Job job) {
        if (job == null)
            throw new NullPointerException("job");
        boundJobs.remove(job);
        job.removeExceptionListener(this);
        job.removeStatusChangeListener(this);
        job.removeProgressChangeListener(this);
        job.removeDurationChangeListener(this);
    }

}

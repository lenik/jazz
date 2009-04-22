package net.bodz.bas.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.lang.RecoverableExceptionListener;
import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.bas.types.TreeNode;
import net.bodz.bas.types.util.Objects;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.ui.UserInterface;

public abstract class _Job implements Job, TreeNode<_Job> {

    protected UserInterface                    UI = ConsoleUI.stdout;
    protected LogTerm                          L  = LogTerms.console;

    private List<_Job>                         children;
    private List<ChildJobAdapter>              linkers;

    private Object                             status;
    private int                                progressIndex;
    private int                                progressSize;
    private Long                               durationSum;

    private List<RecoverableExceptionListener> exceptionListeners;
    private List<StatusChangeListener>         statusChangeListeners;
    private List<ProgressChangeListener>       progressChangeListeners;
    private List<DurationChangeListener>       durationChangeListeners;

    @Override
    public void setUserInterface(UserInterface userInterface) {
        if (userInterface == null)
            throw new NullPointerException("interaction");
        this.UI = userInterface;
    }

    @Override
    public void setLogger(LogTerm logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.L = logger;
    }

    @Override
    public Object getStatus() {
        return status;
    }

    protected void setStatus(Object status) {
        if (Objects.equals(this.status, status)) {
            this.status = status;
            StatusChangeEvent e = new StatusChangeEvent(this, status);
            fireStatusChange(e);
        }
    }

    public double getProgress() {
        if (progressSize == 0)
            return 0.0;
        return (double) progressIndex / progressSize;
    }

    private double getProgress(double progressIncrement) {
        if (progressSize == 0)
            return 0;
        return (progressIndex + progressIncrement) / progressSize;
    }

    protected void setProgress(int progressIndex, int progressSize) {
        setProgressIndex(progressIndex);
        setProgressSize(progressSize);
    }

    @Override
    public int getProgressIndex() {
        return progressIndex;
    }

    protected void setProgressIndex(int progressIndex) {
        if (this.progressIndex != progressIndex) {
            this.progressIndex = progressIndex;
            fireProgressChange(new ProgressChangeEvent(this, getProgress()));
        }
    }

    public int getProgressSize() {
        return progressSize;
    }

    protected void setProgressSize(int progressSize) {
        if (this.progressSize != progressSize) {
            this.progressSize = progressSize;
            fireProgressChange(new ProgressChangeEvent(this, getProgress()));
        }
    }

    /**
     * Override this method to get a better estimated duration this job will
     * take to complete.
     * 
     * @return 0 in default implementation.
     */
    protected long getLocalDuration() {
        return 0;
    }

    // synchronized?
    @Override
    public long getDuration() {
        if (durationSum == null) {
            long sum = getLocalDuration();
            List<? extends Job> children = getChildren();
            if (children != null)
                for (Job child : children)
                    sum += child.getDuration();
            durationSum = sum;
        }
        return durationSum;
    }

    protected void setDuration(long duration) {
        long oldDuration = getDuration();
        if (oldDuration != duration) {
            this.durationSum = duration;
            DurationChangeEvent e = new DurationChangeEvent(this, oldDuration,
                    duration);
            fireDurationChange(e);
        }
    }

    /**
     * Children jobs are executed after this job.
     */
    @Override
    public List<? extends _Job> getChildren() {
        if (children == null)
            return null;
        return children;
    }

    public void addChildJob(_Job job, double progressIncrement) {
        if (children == null) {
            children = new ArrayList<_Job>();
            linkers = new ArrayList<ChildJobAdapter>();
        }
        children.add(job);
        ChildJobAdapter linker = new ChildJobAdapter(progressIncrement);
        linker.bind(job);
        linkers.add(linker);
    }

    public void removeChildJob(_Job job) {
        if (children != null) {
            int index = children.indexOf(job);
            children.remove(index);
            linkers.remove(index);
        }
    }

    protected void execute(Job child, double progressIncrement) {
        if (progressIncrement < 0)
            throw new OutOfDomainException("progressIncrement",
                    progressIncrement, 0);
        ChildJobAdapter linker = new ChildJobAdapter(progressIncrement);
        linker.bind(child);
        try {
            child.run();
        } finally {
            linker.unbind(child);
        }
    }

    @Override
    public void run() {
        _run();
        if (children != null)
            for (_Job child : children) {
                child.run();
            }
    }

    protected abstract void _run();

    protected class ChildJobAdapter extends JobAdaptor {

        private double progressIncrement;

        public ChildJobAdapter(double progressIncrement) {
            this.progressIncrement = progressIncrement;
        }

        @Override
        public void exceptionThrown(Exception ex) {
            if (exceptionListeners != null)
                for (RecoverableExceptionListener listener : exceptionListeners)
                    listener.exceptionThrown(ex);
        }

        @Override
        public void recoverException(RecoverableExceptionEvent e) {
            if (exceptionListeners != null)
                for (RecoverableExceptionListener listener : exceptionListeners) {
                    if (e.isRecovered())
                        break;
                    listener.recoverException(e);
                }
        }

        /**
         * Default implementation won't change the actual status of parent job.
         */
        @Override
        public void statusChange(StatusChangeEvent e) {
            fireStatusChange(e);
        }

        /**
         * Default implementation won't change the actual progress of parent
         * job.
         */
        @Override
        public void progressChange(ProgressChangeEvent e) {
            double localProgress = e.getProgress();
            double progress = getProgress(progressIncrement * localProgress);
            fireProgressChange(new ProgressChangeEvent(e.getSource(), progress));
        }

        @Override
        public void durationChange(DurationChangeEvent e) {
            long newDuration = getDuration() + e.getIncrement();
            setDuration(newDuration);
        }

    }

    @Override
    public void addExceptionListener(RecoverableExceptionListener listener) {
        if (exceptionListeners == null)
            exceptionListeners = new ArrayList<RecoverableExceptionListener>(1);
        exceptionListeners.add(listener);
    }

    @Override
    public void removeExceptionListener(RecoverableExceptionListener listener) {
        if (exceptionListeners != null)
            exceptionListeners.remove(listener);
    }

    /**
     * Combinations: A = recoverable, D = recovered
     * <ul>
     * <li>A & D: default ignored exception
     * <li>A & &#172;D: recoverable exception
     * <li>&#172;A & D: (n/a)
     * <li>&#172;A & &#172;D: fatal exception
     * <li>recover
     * 
     * @return <code>true</code> if the exception is recovered.
     */
    protected boolean defaultExceptionHandler(Exception exception,
            boolean recoverable, boolean discarded) {
        exception.printStackTrace();
        return discarded;
    }

    protected final boolean recoverException(Exception exception,
            boolean discarded) {
        if (exceptionListeners == null)
            return defaultExceptionHandler(exception, true, discarded);
        RecoverableExceptionEvent event = new RecoverableExceptionEvent(this,
                exception);
        event.setRecovered(discarded);
        for (RecoverableExceptionListener listener : exceptionListeners)
            listener.recoverException(event);
        return event.isRecovered();
    }

    /**
     * @return <code>true</code> if the exception is recovered.
     */
    protected final boolean recoverException(Exception exception) {
        return recoverException(exception, false);
    }

    protected final void throwException(Exception exception) {
        if (exceptionListeners == null)
            defaultExceptionHandler(exception, false, false);
        else
            for (RecoverableExceptionListener listener : exceptionListeners)
                listener.exceptionThrown(exception);
    }

    @Override
    public void addStatusChangeListener(StatusChangeListener listener) {
        if (statusChangeListeners == null)
            statusChangeListeners = new ArrayList<StatusChangeListener>(1);
        statusChangeListeners.add(listener);
    }

    @Override
    public void removeStatusChangeListener(StatusChangeListener listener) {
        if (statusChangeListeners != null)
            statusChangeListeners.remove(listener);
    }

    protected final void fireStatusChange(StatusChangeEvent event) {
        if (statusChangeListeners != null)
            for (StatusChangeListener listener : statusChangeListeners)
                listener.statusChange(event);
    }

    @Override
    public void addProgressChangeListener(ProgressChangeListener listener) {
        if (progressChangeListeners == null)
            progressChangeListeners = new ArrayList<ProgressChangeListener>(1);
        progressChangeListeners.add(listener);
    }

    @Override
    public void removeProgressChangeListener(ProgressChangeListener listener) {
        if (progressChangeListeners != null)
            progressChangeListeners.remove(listener);
    }

    protected final void fireProgressChange(ProgressChangeEvent event) {
        if (progressChangeListeners != null)
            for (ProgressChangeListener listener : progressChangeListeners)
                listener.progressChange(event);
    }

    @Override
    public void addDurationChangeListener(DurationChangeListener listener) {
        if (durationChangeListeners == null)
            durationChangeListeners = new ArrayList<DurationChangeListener>(1);
        durationChangeListeners.add(listener);
    }

    @Override
    public void removeDurationChangeListener(DurationChangeListener listener) {
        if (durationChangeListeners != null)
            durationChangeListeners.remove(listener);
    }

    protected final void fireDurationChange(DurationChangeEvent event) {
        if (durationChangeListeners != null)
            for (DurationChangeListener listener : durationChangeListeners)
                listener.durationChange(event);
    }

}

package net.bodz.bas.util;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.collection.tree.TreeNode;
import net.bodz.bas.lang.events.RecoverableExceptionEvent;
import net.bodz.bas.lang.events.RecoverableExceptionListener;
import net.bodz.bas.log.api.Logger;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.ui.UserInterface;
import net.bodz.bas.util.exception.NotImplementedException;
import net.bodz.bas.util.exception.OutOfDomainException;

public abstract class AbstractJob
        implements Job, TreeNode<AbstractJob> {

    protected UserInterface UI = ConsoleUI.stdout;
    protected Logger logger;

    private int state = NOTSTART;

    private List<AbstractJob> children;
    private List<ChildObserver> observers;
    private List<Runnable> exitHooks;

    private Object status;
    private int progressIndex;
    private int progressSize;
    private Long durationSum;

    private List<RecoverableExceptionListener> exceptionListeners;
    private List<StatusChangeListener> statusChangeListeners;
    private List<ProgressChangeListener> progressChangeListeners;
    private List<DurationChangeListener> durationChangeListeners;

    protected abstract void _run();

    @Override
    public void run() {
        switch (state) {
        case NOTSTART:
            setState(RUNNING);
            break;
        case RUNNING:
            return;
        case TERMINATED: // can restart?
        case STOPPED: // can error-restart?
            setState(RUNNING);
            break;
        case STOPPING: // synchronized??
            setState(RUNNING);
            return;
        case PAUSED:
        case PAUSING:
            throw new NotImplementedException();
        }
        try {
            runTree();
            if (state == STOPPING)
                setState(STOPPED);
            else
                setState(TERMINATED);
        } finally {
            if (exitHooks != null)
                for (Runnable exitHook : exitHooks)
                    try {
                        exitHook.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
        }
    }

    /**
     * Override this method to specify your own run order, and set progress indexes for child jobs.
     */
    protected void runTree() {
        _run();
        if (children != null)
            for (AbstractJob child : children) {
                child.execute();
            }
    }

    @Override
    public int getState() {
        return state;
    }

    private void setState(int state) {
        List<? extends Job> children = getChildren();
        if (children != null)
            for (Job child : children)
                switch (state) {
                case PAUSING:
                    child.pause();
                    break;
                case STOPPING:
                    child.stop();
                    break;
                }
        if (this.state != state) {
            this.state = state;
            fireStateChange();
        }
    }

    @Override
    public void pause() {
        switch (state) {
        case RUNNING:
            setState(PAUSING);
            break;
        case STOPPING:
            // setState(STOPPING | PAUSING);
            break;
        }
    }

    @Override
    public void stop() {
        switch (state) {
        case NOTSTART:
        case TERMINATED:
        case STOPPED:
            break;
        case STOPPING:
            return;
        default:
            setState(STOPPING);
        }
    }

    static int slowdown = 0;

    protected final boolean isStopping() {
        if (slowdown != 0)
            try {
                Thread.sleep(slowdown);
            } catch (InterruptedException e) {
            }
        return getState() == STOPPING;
    }

    protected final void setStopped() {
        setState(STOPPED);
    }

    protected void execute(Job child, double progressIncrement) {
        if (progressIncrement < 0)
            throw new OutOfDomainException("progressIncrement", progressIncrement, 0);
        ChildObserver childObserver = new ChildObserver(progressIncrement);
        childObserver.bind(child);
        try {
            child.execute();
        } finally {
            childObserver.unbind(child);
        }
    }

    public void addExitHook(Runnable hook) {
        if (exitHooks == null)
            exitHooks = new ArrayList<Runnable>();
        exitHooks.add(hook);
    }

    @Override
    public void setUserInterface(UserInterface userInterface) {
        if (userInterface == null)
            throw new NullPointerException("interaction");
        this.UI = userInterface;
    }

    @Override
    public void setLogger(Logger logger) {
        if (logger == null)
            throw new NullPointerException("logger");
        this.logger = logger;
    }

    @Override
    public Object getStatus() {
        return status;
    }

    protected void setStatus(Object status) {
        if (Nullables.equals(this.status, status)) {
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

    protected void setProgress(double progress) {
        progressIndex = (int) (progress * progressSize);
        fireProgressChange(progress);
    }

    protected void setProgress(int progressIndex, int progressSize) {
        this.progressIndex = progressIndex;
        this.progressSize = progressSize;
        fireProgressChange();
    }

    @Override
    public int getProgressIndex() {
        return progressIndex;
    }

    protected void setProgressIndex(int progressIndex) {
        if (this.progressIndex != progressIndex) {
            this.progressIndex = progressIndex;
            fireProgressChange();
        }
    }

    protected void setProgressIndex(double progressIndex) {
        this.progressIndex = (int) progressIndex;
        double progress = progressSize == 0 ? 0 : progressIndex / progressSize;
        fireProgressChange(progress);
    }

    public int getProgressSize() {
        return progressSize;
    }

    protected void setProgressSize(int progressSize) {
        if (this.progressSize != progressSize) {
            this.progressSize = progressSize;
            fireProgressChange();
        }
    }

    /**
     * Set progress index, status info, and check if stopping requested.
     */
    protected boolean moveOn(double progressIndex, Object status) {
        setStatus(status);
        return moveOn(progressIndex);
    }

    protected boolean moveOn(double progressIndex) {
        setProgressIndex(progressIndex);
        if (isStopping()) {
            setStopped();
            return false;
        }
        return true;
    }

    /**
     * Override this method to get a better estimated duration this job will take to complete.
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
            DurationChangeEvent e = new DurationChangeEvent(this, oldDuration, duration);
            fireDurationChange(e);
        }
    }

    /**
     * Children jobs are executed after this job.
     */
    @Override
    public List<? extends AbstractJob> getChildren() {
        if (children == null)
            return null;
        return children;
    }

    public void addChildJob(AbstractJob job, double progressIncrement) {
        if (children == null) {
            children = new ArrayList<AbstractJob>();
            observers = new ArrayList<ChildObserver>();
        }
        children.add(job);
        ChildObserver observer = new ChildObserver(progressIncrement);
        observer.bind(job);
        observers.add(observer);
    }

    public boolean removeChildJob(AbstractJob job) {
        if (children == null)
            return false;
        int index = children.indexOf(job);
        if (index == -1)
            return false;
        ChildObserver observer = observers.remove(index);
        observer.unbind(job);
        return true;
    }

    protected class ChildObserver
            extends JobObserver {

        private double progressIncrement;

        public ChildObserver(double progressIncrement) {
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
         * Default implementation won't change the actual progress of parent job.
         */
        @Override
        public void progressChange(ProgressChangeEvent e) {
            double childLocal = e.getProgress();
            double progress = getProgress(progressIncrement * childLocal);
            fireProgressChange(new ProgressChangeEvent(e.getSource(), progress));
        }

        @Override
        public void durationChange(DurationChangeEvent e) {
            long newDuration = getDuration() + e.getIncrement();
            setDuration(newDuration);
        }

    }

    private List<StateChangeListener> StateChangeListeners;

    @Override
    public void addStateChangeListener(StateChangeListener listener) {
        if (StateChangeListeners == null)
            StateChangeListeners = new ArrayList<StateChangeListener>(1);
        StateChangeListeners.add(listener);
    }

    @Override
    public void removeStateChangeListener(StateChangeListener listener) {
        if (StateChangeListeners != null)
            StateChangeListeners.remove(listener);
    }

    protected final void fireStateChange() {
        if (StateChangeListeners != null) {
            StateChangeEvent event = new StateChangeEvent(this);
            for (StateChangeListener listener : StateChangeListeners)
                listener.stateChange(event);
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
    protected boolean defaultExceptionHandler(Exception exception, boolean recoverable, boolean discarded) {
        exception.printStackTrace();
        return discarded;
    }

    protected final boolean recoverException(Exception exception, boolean discarded) {
        if (exceptionListeners == null)
            return defaultExceptionHandler(exception, true, discarded);
        RecoverableExceptionEvent event = new RecoverableExceptionEvent(this, exception);
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

    protected final void fireProgressChange() {
        fireProgressChange(getProgress());
    }

    protected final void fireProgressChange(double progress) {
        ProgressChangeEvent event = new ProgressChangeEvent(this, progress);
        fireProgressChange(event);
    }

    protected final void fireProgressChange(ProgressChangeEvent event) {
        if (progressChangeListeners != null) {
            for (ProgressChangeListener listener : progressChangeListeners)
                listener.progressChange(event);
        }
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

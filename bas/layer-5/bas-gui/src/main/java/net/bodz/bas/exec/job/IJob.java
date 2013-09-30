package net.bodz.bas.exec.job;

import java.util.logging.Logger;

import net.bodz.bas.err.IRecoverableExceptionListener;
import net.bodz.bas.err.RecoverableExceptionSource;

public interface IJob
        extends RecoverableExceptionSource {

    int NOTSTART = 0;
    int RUNNING = 1;
    int STOPPING = 2;
    int STOPPED = 3;
    int PAUSING = 4;
    int PAUSED = 5;
    int TERMINATED = 6;

    void start();

    void stop();

    void pause();

    int getState();

    void addStateChangeListener(StateChangeListener listener);

    void removeStateChangeListener(StateChangeListener listener);

    @Override
    void addExceptionListener(IRecoverableExceptionListener listener);

    @Override
    void removeExceptionListener(IRecoverableExceptionListener listener);

    // void setCancel(boolean cancel);

    /**
     * @return Object whose Object#getString will be called.
     */
    Object getStatus();

    void addStatusChangeListener(IStatusChangeListener listener);

    void removeStatusChangeListener(IStatusChangeListener listener);

    int getProgressIndex();

    int getProgressSize();

    /**
     * Estimated milliseconds this job will take to complete.
     */
    long getDuration();

    void addProgressChangeListener(IProgressChangeListener listener);

    void removeProgressChangeListener(IProgressChangeListener listener);

    void addDurationChangeListener(IDurationChangeListener listener);

    void removeDurationChangeListener(IDurationChangeListener listener);

}

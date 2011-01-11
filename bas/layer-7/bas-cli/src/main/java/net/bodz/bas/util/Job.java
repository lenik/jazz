package net.bodz.bas.util;

import net.bodz.bas.io.term.LogTerm;
import net.bodz.bas.lang.events.RecoverableExceptionListener;
import net.bodz.bas.ui.UserInterface;

public interface Job
        extends ExceptionSourceRunnable {

    int NOTSTART = 0;
    int RUNNING = 1;
    int STOPPING = 2;
    int STOPPED = 3;
    int PAUSING = 4;
    int PAUSED = 5;
    int TERMINATED = 6;

    @Override
    void run();

    void stop();

    void pause();

    int getState();

    void addStateChangeListener(StateChangeListener listener);

    void removeStateChangeListener(StateChangeListener listener);

    @Override
    void addExceptionListener(RecoverableExceptionListener listener);

    @Override
    void removeExceptionListener(RecoverableExceptionListener listener);

    // void setCancel(boolean cancel);

    /**
     * @return Object whose Object#getString will be called.
     */
    Object getStatus();

    void addStatusChangeListener(StatusChangeListener listener);

    void removeStatusChangeListener(StatusChangeListener listener);

    int getProgressIndex();

    int getProgressSize();

    /**
     * Estimated milliseconds this job will take to complete.
     */
    long getDuration();

    void addProgressChangeListener(ProgressChangeListener listener);

    void removeProgressChangeListener(ProgressChangeListener listener);

    void addDurationChangeListener(DurationChangeListener listener);

    void removeDurationChangeListener(DurationChangeListener listener);

    void setUserInterface(UserInterface userInterface);

    void setLogger(LogTerm logger);

}

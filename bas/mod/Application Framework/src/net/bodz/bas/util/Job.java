package net.bodz.bas.util;

import net.bodz.bas.lang.ExceptionSourceRunnable;
import net.bodz.bas.lang.RecoverableExceptionListener;
import net.bodz.bas.ui.UserInterface;

public interface Job extends ExceptionSourceRunnable {

    @Override
    void run();

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

package net.bodz.bas.ui.model.action;

import net.bodz.bas.log.ILogger;
import net.bodz.bas.log.Logger;

public interface IProgressMonitor {

    int UNKNOWN = -1;

    /**
     * @param parentDiv
     *            Amount of progress in the parent job.
     */
    void enter(String taskName, int parentDiv, int totalProgress, long estimatedDuration);

    void leave();

    int getTotalProgress();

    void setTotalProgress(int totalProgress);

    int getProgress();

    void addProgress(int delta);

    void setProgress(int progress);

    void addCancelListener(ICancelListener cancelListener);

    void removeCancelListener(ICancelListener cancelListener);

    ILogger getLogger();

    NullProgressMonitor NULL = new NullProgressMonitor();

}

class NullProgressMonitor
        implements IProgressMonitor {

    @Override
    public void enter(String taskName, int amountOfParentWork, int totalWork, long estimatedDuration) {
    }

    @Override
    public void leave() {
    }

    @Override
    public int getTotalProgress() {
        return 1;
    }

    @Override
    public void setTotalProgress(int totalProrgress) {
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public void addProgress(int delta) {
    }

    @Override
    public void setProgress(int progress) {
    }

    @Override
    public void addCancelListener(ICancelListener cancelListener) {
    }

    @Override
    public void removeCancelListener(ICancelListener cancelListener) {
    }

    @Override
    public ILogger getLogger() {
        return Logger.NULL;
    }

}
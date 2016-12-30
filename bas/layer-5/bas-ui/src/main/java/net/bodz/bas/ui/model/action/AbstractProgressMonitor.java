package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.AbstractLogger2;
import net.bodz.bas.log.ILogger;

public abstract class AbstractProgressMonitor
        extends AbstractLogger2
        implements IProgressMonitor {

    private int totalProgress;
    private int progress;
    private List<ICancelListener> cancelListeners = new ArrayList<>();

    @Override
    public int getTotalProgress() {
        return totalProgress;
    }

    @Override
    public void setTotalProgress(int totalProgress) {
        this.totalProgress = totalProgress;
        updateProgress();
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public void addProgress(int delta) {
        this.progress += delta;
        updateProgress();
    }

    @Override
    public void setProgress(int progress) {
        this.progress = progress;
        updateProgress();
    }

    protected void updateProgress() {
    }

    @Override
    public void addCancelListener(ICancelListener cancelListener) {
        if (cancelListener == null)
            throw new NullPointerException("cancelListener");
        cancelListeners.add(cancelListener);
    }

    @Override
    public void removeCancelListener(ICancelListener cancelListener) {
        cancelListeners.remove(cancelListener);
    }

    public void cancel() {
        for (ICancelListener listener : cancelListeners)
            listener.cancel();
    }

    @Override
    public ILogger getLogger() {
        return this;
    }

}

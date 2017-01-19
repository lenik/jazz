package net.bodz.bas.ui.model.action;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.log.AbstractLinearLogger;
import net.bodz.bas.log.ILogger;

public abstract class AbstractProgressMonitor
        extends AbstractLinearLogger
        implements IProgressMonitor {

    private int totalProgress;
    private int progress;

    private List<ICancelListener> cancelListeners = new ArrayList<>();
    private List<IProgressChangeListener> progressChangeListeners = new ArrayList<>();

    @Override
    public int getTotalProgress() {
        return totalProgress;
    }

    @Override
    public void setTotalProgress(int totalProgress) {
        if (this.totalProgress != totalProgress) {
            this.totalProgress = totalProgress;
            progressChange();
        }
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public void addProgress(int delta) {
        if (delta != 0) {
            this.progress += delta;
            progressChange();
        }
    }

    @Override
    public void setProgress(int progress) {
        if (this.progress != progress) {
            this.progress = progress;
            progressChange();
        }
    }

    void progressChange() {
        for (IProgressChangeListener listener : progressChangeListeners)
            listener.progressChange(totalProgress, progress);
    }

    public void cancel() {
        for (ICancelListener listener : cancelListeners)
            listener.cancel();
    }

    @Override
    public void addProgressChangeListener(IProgressChangeListener progressChangeListener) {
        if (progressChangeListener == null)
            throw new NullPointerException("progressChangeListener");
        progressChangeListeners.add(progressChangeListener);
    }

    @Override
    public void removeProgressChangeListener(IProgressChangeListener progressChangeListener) {
        progressChangeListeners.remove(progressChangeListener);
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

    @Override
    public ILogger getLogger() {
        return this;
    }

}

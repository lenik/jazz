package net.bodz.bas.ui.model.action;

import net.bodz.bas.log.ILogger;
import net.bodz.bas.log.Logger;

public interface IProgressMonitor {

    int UNKNOWN = -1;

    void enter(String taskName, int amountOfParentWork, int totalWork, long estimatedDuration);

    void leave();

    // void setTotalWork(int totalWork);

    /**
     * @return cancel state.
     */
    boolean worked(int amount);

    boolean isCanceled();

    void setCanceled(boolean canceled);

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
    public boolean worked(int amount) {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void setCanceled(boolean canceled) {
    }

    @Override
    public ILogger getLogger() {
        return Logger.NULL;
    }

}
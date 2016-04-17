package net.bodz.bas.ui.model.action;

import net.bodz.bas.log.ILogger;

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

}

package net.bodz.bas.c.action;

public interface IProgressMonitor {

    int UNKNOWN = -1;

    void enter(String taskName, int amountOfParentWork, int totalWork, long estimatedDuration);

    void leave();

    void worked(int amount);

    void isCanceled();

    void setCanceled(boolean canceled);

    void info(String message);

    void warn(String message);

    /**
     * @return <code>true</code> for retry, <code>false</code> for ignore or cancel.
     */
    boolean error(String message, Throwable exception);

}

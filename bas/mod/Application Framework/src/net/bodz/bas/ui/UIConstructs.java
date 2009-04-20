package net.bodz.bas.ui;

import net.bodz.bas.lang.RunnableThrows;

public interface UIConstructs {

    /**
     * @param maxRetry
     *            retry any times if {@link TryBlock#INFINITE}.
     * @return
     * @see TryBlock#DONE
     * @see TryBlock#MAXRETRIED
     * @see TryBlock#IGNORED
     * @see TryBlock#CANCELED
     */
    int tryBlock(RunnableThrows<? extends Exception> runnable, int maxRetry);

    // void showProgress(Runnable runnable);

    void showBusy(Runnable runnable);

}

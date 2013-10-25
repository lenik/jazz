package net.bodz.bas.c.action;

import net.bodz.bas.log.AbstractLogger2;
import net.bodz.bas.log.ILogger;

public abstract class AbstractProgressMonitor
        extends AbstractLogger2
        implements IProgressMonitor {

    private boolean canceled;

    @Override
    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    @Override
    public ILogger getLogger() {
        return this;
    }

}

package net.bodz.pkg.sis;

import java.util.Arrays;

import net.bodz.bas.c.action.AbstractProgressMonitor;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.impl.ConsoleLogger;
import net.bodz.bas.ui.dialog.ConsoleDialogs;
import net.bodz.bas.ui.dialog.IUserDialogs;

public class ConsoleProgressMonitor
        extends AbstractProgressMonitor {

    static final Logger logger = ConsoleLogger.getInstance();

    IUserDialogs dialogs = ConsoleDialogs.stdout;

    @Override
    public void enter(String taskName, int amountOfParentWork, int totalWork, long estimatedDuration) {
    }

    @Override
    public void leave() {
    }

    @Override
    public boolean worked(int amount) {
        // double progress = 100 * e.getProgress();
        // String mesg = String.format(tr._("Progress %.2f%%"), progress);
        logger.mesg("Progress: " + amount);
        return isCanceled();
    }

    @Override
    public boolean _error(int delta, Throwable e, Object message) {
        // logger.warn(message, e);
        int choice = dialogs.choice("Continue?", Arrays.asList("Retry", "Ignore", "Cancel"));
        switch (choice) {
        case 0: // Retry
            return true;
        case 1: // Ignore
            return false;
        case 2: // Cancel
        default:
            setCanceled(true);
            return false;
        }
    }

    @Override
    public void _warn(int delta, Throwable e, Object message) {
        logger._warn(delta, e, message);
    }

    @Override
    public void _info(int delta, Throwable e, Object message) {
        logger._info(delta, e, message);
    }

    @Override
    public void _debug(int delta, Throwable e, Object message) {
        logger._debug(delta, e, message);
    }

    @Override
    public void _status(int delta, Throwable e, Object message) {
        logger._status(delta, e, message);
    }

}

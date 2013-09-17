package net.bodz.pkg.installer;

import net.bodz.bas.err.RecoverableExceptionEvent;
import net.bodz.bas.exec.job.DurationChangeEvent;
import net.bodz.bas.exec.job.ProgressChangeEvent;
import net.bodz.bas.exec.job.StatusChangeEvent;
import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.gui.dialog.DirectiveCommands;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.impl.ConsoleLogger;

public class ConsoleExecutor
        extends ProjectExecutor {

    public ConsoleExecutor(IProject project) {
        this(project, ConsoleLogger.getInstance());
    }

    public ConsoleExecutor(IProject project, Logger logger) {
        super(project, ConsoleDialogs.stdout, logger);
    }

    @Override
    public void exceptionThrown(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void recoverException(RecoverableExceptionEvent e) {
        Exception ex = e.getException();
        int answer = userDialogs.ask(ex.getMessage(), ex, DirectiveCommands.ignore, DirectiveCommands.cancel);
        if (answer == 0) // ignore
            e.setRecovered(true);
    }

    @Override
    public void statusChange(StatusChangeEvent e) {
        logger.mesg(e.getStatus());
    }

    @Override
    public void progressChange(ProgressChangeEvent e) {
        double progress = 100 * e.getProgress();
        String mesg = String.format(tr._("Progress %.2f%%"), progress);
        logger.info(mesg);
    }

    @Override
    public void durationChange(DurationChangeEvent e) {
        // getTimeBegin();
        // long d = job.getDuration()
        // ...
        String mesg = String.format(tr._("Duration Changed: %d"), e.getIncrement());
        logger.info(mesg);
    }

}

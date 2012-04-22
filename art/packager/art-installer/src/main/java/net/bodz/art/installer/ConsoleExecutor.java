package net.bodz.art.installer;

import net.bodz.art.installer.nls.PackNLS;
import net.bodz.bas.log.api.Logger;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.util.DurationChangeEvent;
import net.bodz.bas.util.ProgressChangeEvent;
import net.bodz.bas.util.StatusChangeEvent;
import net.bodz.bas.util.exception.RecoverableExceptionEvent;

/**
 * @test {@link PackageTaskTest}
 * @test {@link ConsoleExecutorTest}
 */
public class ConsoleExecutor
        extends ProjectExecutor {

    public ConsoleExecutor(IProject project) {
        this(project, LogTerms.console);
    }

    public ConsoleExecutor(IProject project, Logger logger) {
        super(project, ConsoleUI.stdout, logger);
    }

    @Override
    public void exceptionThrown(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void recoverException(RecoverableExceptionEvent e) {
        Exception ex = e.getException();
        int answer = UI.ask(ex.getMessage(), ex, Proposals.ignore, Proposals.cancel);
        if (answer == 0) // ignore
            e.setRecovered(true);
    }

    @Override
    public void statusChange(StatusChangeEvent e) {
        L.mesg(e.getStatus());
    }

    @Override
    public void progressChange(ProgressChangeEvent e) {
        double progress = 100 * e.getProgress();
        String mesg = String.format(PackNLS.getString("ConsoleExecutor.progress_f"), progress); //$NON-NLS-1$
        L.tinfo(mesg);
    }

    @Override
    public void durationChange(DurationChangeEvent e) {
        // getTimeBegin();
        // long d = job.getDuration()
        // ...
        String mesg = String.format(PackNLS.getString("ConsoleExecutor.durationChanged_d"), e.getIncrement()); //$NON-NLS-1$
        L.tinfo(mesg);
    }

}

package net.bodz.dist.ins;

import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.util.DurationChangeEvent;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.util.LogTerms;
import net.bodz.bas.util.ProgressChangeEvent;
import net.bodz.bas.util.StatusChangeEvent;
import net.bodz.dist.nls.PackNLS;

/**
 * @test {@link PackageTaskTest}
 * @test {@link ConsoleExecutorTest}
 */
public class ConsoleExecutor extends ProjectExecutor {

    public ConsoleExecutor(Project project) {
        this(project, LogTerms.console);
    }

    public ConsoleExecutor(Project project, LogTerm logger) {
        super(project, ConsoleUI.stdout, logger);
    }

    @Override
    public void exceptionThrown(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void recoverException(RecoverableExceptionEvent e) {
        Exception ex = e.getException();
        int answer = UI.ask(ex.getMessage(), e, Proposals.ignore, Proposals.cancel);
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

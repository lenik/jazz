package net.bodz.dist.ins;

import net.bodz.bas.lang.RecoverableExceptionEvent;
import net.bodz.bas.ui.ConsoleUI;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.util.DurationChangeEvent;
import net.bodz.bas.util.LogTerm;
import net.bodz.bas.util.LogTerms;
import net.bodz.bas.util.ProgressChangeEvent;
import net.bodz.bas.util.StatusChangeEvent;

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
        int answer = UI.ask(ex.getMessage(), e, Proposals.ignore,
                Proposals.cancel);
        if (answer == 0) // ignore
            e.setRecovered(true);
    }

    @Override
    public void statusChange(StatusChangeEvent e) {
        L.mesg(e.getStatus());
    }

    @Override
    public void progressChange(ProgressChangeEvent e) {
        L.tdetail("Progress %.1f%%", e.getProgress());
    }

    @Override
    public void durationChange(DurationChangeEvent e) {
        // getTimeBegin();
        // long d = job.getDuration()
        // ...
        L.tdetail("Duration Changed: %d", e.getIncrement());
    }

}

package net.bodz.bas.ui;

import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.nls.AppNLS;

/**
 * @test
 */
public abstract class _TryBlock extends TryBlock {

    protected final UserInterface UI;

    public _TryBlock(UserInterface ui, int maxRetry, boolean tryImmediately) {
        super(maxRetry, false);
        if (ui == null)
            throw new NullPointerException("interaction"); //$NON-NLS-1$
        this.UI = ui;
        if (tryImmediately)
            _run();
    }

    public _TryBlock(UserInterface ui) {
        this(ui, INFINITE, true);
    }

    public _TryBlock(UserInterface ui, int maxRetry) {
        this(ui, maxRetry, true);
    }

    @Override
    protected int ask(Exception e) {
        int answer = UI.ask(e.getMessage(), e, //
                Proposals.retry, Proposals.ignore, Proposals.cancel, Proposals.debug);
        switch (answer) {
        case 0:
            return RETRY;
        case 1:
            return IGNORE;
        case 2:
            return CANCEL;
        case 3:
            throw new RuntimeException("Debug"); //$NON-NLS-1$
        }
        throw new UnexpectedException();
    }

}

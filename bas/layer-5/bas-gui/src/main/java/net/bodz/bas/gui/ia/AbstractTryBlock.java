package net.bodz.bas.gui.ia;

import net.bodz.bas.err.UnexpectedException;

public abstract class AbstractTryBlock
        extends TryBlock {

    protected final IUserInteraction UI;

    public AbstractTryBlock(IUserInteraction ui, int maxRetry, boolean tryImmediately) {
        super(maxRetry, false);
        if (ui == null)
            throw new NullPointerException("interaction");
        this.UI = ui;
        if (tryImmediately)
            _run();
    }

    public AbstractTryBlock(IUserInteraction ui) {
        this(ui, INFINITE, true);
    }

    public AbstractTryBlock(IUserInteraction ui, int maxRetry) {
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
            throw new RuntimeException("Debug");
        }
        throw new UnexpectedException();
    }

}
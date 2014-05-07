package net.bodz.bas.ui.dialog;

import net.bodz.bas.err.UnexpectedException;

public abstract class AbstractTryBlock
        extends TryBlock {

    protected final IUserDialogs UI;

    public AbstractTryBlock(IUserDialogs ui, int maxRetry) {
        super(maxRetry);
        if (ui == null)
            throw new NullPointerException("interaction");
        this.UI = ui;
    }

    @Override
    protected int ask(Exception e) {
        int answer = UI.ask(e.getMessage(), e, //
                DirectiveCommands.retry, DirectiveCommands.ignore, DirectiveCommands.cancel, DirectiveCommands.debug);
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

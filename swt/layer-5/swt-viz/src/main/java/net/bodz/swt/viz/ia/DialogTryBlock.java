package net.bodz.swt.viz.ia;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.gui.ia.AbstractTryBlock;
import net.bodz.bas.gui.ia.IUserInteraction;
import net.bodz.bas.gui.ia.Proposals;
import net.bodz.swt.c3.ia.DialogInteraction;

public abstract class DialogTryBlock
        extends AbstractTryBlock {

    public DialogTryBlock(IUserInteraction userInterface, int maxRetry, boolean tryImmediately) {
        super(userInterface, maxRetry, tryImmediately);
    }

    public DialogTryBlock(IUserInteraction userInterface, int maxRetry) {
        super(userInterface, maxRetry);
    }

    public DialogTryBlock(IUserInteraction userInterface) {
        super(userInterface);
    }

    public DialogTryBlock(int maxRetry, boolean tryImmediately) {
        this(new DialogInteraction(), maxRetry, tryImmediately);
    }

    public DialogTryBlock(boolean tryImmediately) {
        this(INFINITE, tryImmediately);
    }

    public DialogTryBlock() {
        this(INFINITE, true);
    }

    public DialogTryBlock(int maxRetry) {
        this(maxRetry, true);
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

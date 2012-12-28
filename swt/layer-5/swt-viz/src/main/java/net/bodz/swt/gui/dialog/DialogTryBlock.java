package net.bodz.swt.gui.dialog;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.gui.dialog.AbstractTryBlock;
import net.bodz.bas.gui.dialog.DirectiveCommands;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.swt.c3.dialog.SwtDialogs;

public abstract class DialogTryBlock
        extends AbstractTryBlock {

    public DialogTryBlock(IUserDialogs userInterface, int maxRetry, boolean tryImmediately) {
        super(userInterface, maxRetry, tryImmediately);
    }

    public DialogTryBlock(IUserDialogs userInterface, int maxRetry) {
        super(userInterface, maxRetry);
    }

    public DialogTryBlock(IUserDialogs userInterface) {
        super(userInterface);
    }

    public DialogTryBlock(int maxRetry, boolean tryImmediately) {
        this(new SwtDialogs(), maxRetry, tryImmediately);
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

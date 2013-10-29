package net.bodz.swt.gui.dialog;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.gui.dialog.AbstractTryBlock;
import net.bodz.bas.gui.dialog.DirectiveCommands;
import net.bodz.bas.gui.dialog.IUserDialogs;
import net.bodz.swt.c.dialog.SwtUserDialogs;

public abstract class DialogTryBlock
        extends AbstractTryBlock {

    public DialogTryBlock(IUserDialogs userInterface, int maxRetry) {
        super(userInterface, maxRetry);
    }

    public DialogTryBlock(IUserDialogs userInterface) {
        super(userInterface, INFINITE);
    }

    public DialogTryBlock(int maxRetry) {
        this(new SwtUserDialogs(), maxRetry);
    }

    public DialogTryBlock() {
        this(INFINITE);
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

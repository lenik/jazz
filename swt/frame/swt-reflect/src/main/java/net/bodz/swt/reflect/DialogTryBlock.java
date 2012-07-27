package net.bodz.swt.reflect;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.ui.AbstractTryBlock;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.ui.UserInterface;
import net.bodz.swt.reflect.util.DialogUI;

/**
 * @test {@link DialogTryBlockTest}
 */
public abstract class DialogTryBlock
        extends AbstractTryBlock {

    public DialogTryBlock(UserInterface userInterface, int maxRetry, boolean tryImmediately) {
        super(userInterface, maxRetry, tryImmediately);
    }

    public DialogTryBlock(UserInterface userInterface, int maxRetry) {
        super(userInterface, maxRetry);
    }

    public DialogTryBlock(UserInterface userInterface) {
        super(userInterface);
    }

    public DialogTryBlock(int maxRetry, boolean tryImmediately) {
        this(new DialogUI(), maxRetry, tryImmediately);
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

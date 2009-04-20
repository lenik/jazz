package net.bodz.swt.gui;

import net.bodz.bas.lang.err.UnexpectedException;
import net.bodz.bas.ui.Interaction;
import net.bodz.bas.ui.Proposals;
import net.bodz.bas.ui._TryBlock;

/**
 * @test {@link DialogTryBlockTest}
 */
public abstract class DialogTryBlock extends _TryBlock {

    public DialogTryBlock(Interaction interaction, int maxRetry,
            boolean tryImmediately) {
        super(interaction, maxRetry, tryImmediately);
    }

    public DialogTryBlock(Interaction interaction, int maxRetry) {
        super(interaction, maxRetry);
    }

    public DialogTryBlock(Interaction interaction) {
        super(interaction);
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
        int answer = interaction.ask(e.getMessage(), e, //
                Proposals.retry, Proposals.ignore, Proposals.cancel);
        switch (answer) {
        case 0:
            return RETRY;
        case 1:
            return IGNORE;
        case 2:
            return CANCEL;
        }
        throw new UnexpectedException();
    }

}

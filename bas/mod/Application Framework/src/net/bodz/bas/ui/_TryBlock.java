package net.bodz.bas.ui;

import net.bodz.bas.lang.err.UnexpectedException;

/**
 * @test
 */
public abstract class _TryBlock extends TryBlock {

    protected final Interaction interaction;

    public _TryBlock(Interaction interaction, int maxRetry,
            boolean tryImmediately) {
        super(maxRetry, false);
        if (interaction == null)
            throw new NullPointerException("interaction");
        this.interaction = interaction;
        if (tryImmediately)
            _run();
    }

    public _TryBlock(Interaction interaction) {
        this(interaction, INFINITE, true);
    }

    public _TryBlock(Interaction interaction, int maxRetry) {
        this(interaction, maxRetry, true);
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

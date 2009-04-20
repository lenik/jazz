package net.bodz.bas.ui;

import net.bodz.bas.lang.RunnableThrows;

/**
 * @test {@link ConsoleUIConstructsTest}
 */
public class _UIConstructs implements UIConstructs {

    protected final Interaction interaction;

    public _UIConstructs(Interaction interaction) {
        if (interaction == null)
            throw new NullPointerException("interaction");
        this.interaction = interaction;
    }

    public int tryBlock(final RunnableThrows<? extends Exception> runnable) {
        return tryBlock(runnable, TryBlock.INFINITE);
    }

    @Override
    public int tryBlock(final RunnableThrows<? extends Exception> runnable,
            int maxRetry) {
        return new _TryBlock(interaction, maxRetry, false) {
            @Override
            protected void body() throws Exception {
                runnable.run();
            }
        }._run();
    }

    @Override
    public void showBusy(Runnable runnable) {
        runnable.run();
    }

}

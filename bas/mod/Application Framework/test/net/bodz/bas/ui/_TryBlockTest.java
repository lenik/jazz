package net.bodz.bas.ui;

import org.junit.Test;

public class _TryBlockTest {

    @Test
    public void testMaxRetry() throws Exception {
        new _TryBlock(ConsoleUI.stderr, 3) {

            @Override
            protected void body() throws Exception {
                System.out.println("run"); //$NON-NLS-1$
                throw new Exception("run exception"); //$NON-NLS-1$
            }

            @Override
            protected void exit(int exitType) {
                System.out.println("Exit: " + exitType); //$NON-NLS-1$
            }

            @Override
            protected void init() {
                System.out.println("Init"); //$NON-NLS-1$
            }

            @Override
            protected void retry() {
                System.out.println("Retry"); //$NON-NLS-1$
            }

        };
    }

}

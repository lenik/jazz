package net.bodz.swt.gui;

import org.junit.Test;

public class DialogTryBlockTest {

    @Test
    public void testMaxRetry() throws Exception {
        new DialogTryBlock(3) {

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

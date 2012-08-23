package net.bodz.bas.ui;

import org.junit.Test;

import net.bodz.bas.gui.ia.AbstractTryBlock;
import net.bodz.bas.gui.ia.ConsoleInteraction;

public class _TryBlockTest {

    @Test
    public void testMaxRetry()
            throws Exception {
        new AbstractTryBlock(ConsoleInteraction.stderr, 3) {

            @Override
            protected void body()
                    throws Exception {
                System.out.println("run");
                throw new Exception("run exception");
            }

            @Override
            protected void exit(int exitType) {
                System.out.println("Exit: " + exitType);
            }

            @Override
            protected void init() {
                System.out.println("Init");
            }

            @Override
            protected void retry() {
                System.out.println("Retry");
            }

        };
    }

}

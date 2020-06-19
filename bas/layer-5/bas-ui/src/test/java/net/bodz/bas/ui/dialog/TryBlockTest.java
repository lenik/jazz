package net.bodz.bas.ui.dialog;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TryBlockTest
        extends Assert {

    @Ignore
    @Test
    public void retry3Times() {
        TryBlock tryBlock = new AbstractTryBlock(ConsoleDialogs.stderr, 3) {

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
        tryBlock.run();
    }

}

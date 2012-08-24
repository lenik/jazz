package net.bodz.swt.viz.ia;

import org.junit.Test;

import net.bodz.swt.viz.ia.DialogTryBlock;

public class DialogTryBlockTest {

    @Test
    public void testMaxRetry()
            throws Exception {
        new DialogTryBlock(3) {

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

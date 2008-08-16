package net.bodz.bas.sec;

import static org.junit.Assert.*;
import net.bodz.bas.lang.ControlExit;

import org.junit.Test;

public class CatchExitTest {

    static class Exitor implements Runnable {

        private int status;

        public Exitor(int status) {
            this.status = status;
        }

        @Override
        public void run() {
            System.exit(status);
        }

    }

    @Test(expected = ControlExit.class)
    public void test1() {
        Exitor exitor = new Exitor(123);
        CatchExit.run(exitor);
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            try {
                CatchExit.run(new Exitor(i));
            } catch (ControlExit e) {
                assertEquals(i, e.getStatus());
            }
        }
    }

}

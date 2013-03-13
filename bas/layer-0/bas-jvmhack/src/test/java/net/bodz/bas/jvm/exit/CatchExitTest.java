package net.bodz.bas.jvm.exit;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.control.ControlExit;
import net.bodz.bas.fn.IExecutable;

public class CatchExitTest
        extends Assert {

    static class TestProgram
            implements IExecutable {

        private int status;

        public TestProgram(int status) {
            this.status = status;
        }

        public void execute() {
            System.exit(status);
        }

    }

    @Test(expected = ControlExit.class)
    public void test1()
            throws Exception {
        TestProgram program = new TestProgram(123);
        CatchExit catcher = new CatchExit();
        catcher.catchExit(program);
    }

    @Test
    public void test2()
            throws Exception {
        CatchExit catcher = new CatchExit();
        for (int status = 0; status < 10; status++) {
            try {
                catcher.catchExit(new TestProgram(status));
                fail();
            } catch (ControlExit e) {
                assertEquals(status, e.getStatus());
            }
        }
    }

}

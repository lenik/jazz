package net.bodz.bas.jvm.exit;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.closure.IExecutableX;

import org.junit.Test;

public class CatchExitTest {

    static class TestProgram
            implements IExecutableX<Exception> {

        private int status;

        public TestProgram(int status) {
            this.status = status;
        }

        @Override
        public void execute()
                throws Exception {
            System.exit(status);
        }

    }

    @Test(expected = IllegalExitException.class)
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
        for (int i = 0; i < 10; i++) {
            try {
                catcher.catchExit(new TestProgram(i));
            } catch (IllegalExitException e) {
                assertEquals(i, e.getStatus());
            }
        }
    }

}

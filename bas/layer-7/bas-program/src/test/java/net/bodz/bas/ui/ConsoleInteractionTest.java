package net.bodz.bas.ui;

import org.junit.Test;

public class ConsoleInteractionTest {

    @Test
    public void testTryBlock() throws Exception {
        ConsoleUI ci = ConsoleUI.stderr;
        ci.tryBlock(new Executable<Exception>() {
            @Override
            public void execute() throws Exception {
                throw new ExpectedException();
            }
        });
    }

}

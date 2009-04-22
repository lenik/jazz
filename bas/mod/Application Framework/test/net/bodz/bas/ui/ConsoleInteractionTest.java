package net.bodz.bas.ui;

import net.bodz.bas.lang.RunnableThrows;
import net.bodz.bas.lang.err.ExpectedException;

import org.junit.Test;

public class ConsoleInteractionTest {

    @Test
    public void testTryBlock() throws Exception {
        ConsoleUI ci = ConsoleUI.stderr;
        ci.tryBlock(new RunnableThrows<Exception>() {
            @Override
            public void run() throws Exception {
                throw new ExpectedException();
            }
        });
    }

}

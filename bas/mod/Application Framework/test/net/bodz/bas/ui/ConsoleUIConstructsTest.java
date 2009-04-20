package net.bodz.bas.ui;

import net.bodz.bas.lang.RunnableThrows;
import net.bodz.bas.lang.err.ExpectedException;

import org.junit.Test;

public class ConsoleUIConstructsTest {

    @Test
    public void testTryBlock() throws Exception {
        ConsoleInteraction ci = new ConsoleInteraction();
        _UIConstructs ui = new _UIConstructs(ci);
        ui.tryBlock(new RunnableThrows<Exception>() {
            @Override
            public void run() throws Exception {
                throw new ExpectedException();
            }
        });
    }

}

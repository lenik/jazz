package net.bodz.bas.ui;

import org.junit.Test;

import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.model.IExecutableX;

public class ConsoleInteractionTest {

    @Test
    public void testTryBlock()
            throws Exception {
        ConsoleUI ci = ConsoleUI.stderr;
        ci.tryBlock(new IExecutableX<Exception>() {
            @Override
            public void execute()
                    throws Exception {
                throw new ExpectedException();
            }
        });
    }

}

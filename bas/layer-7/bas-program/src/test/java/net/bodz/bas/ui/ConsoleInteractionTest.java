package net.bodz.bas.ui;

import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.util.arch.IExecutableX;

import org.junit.Test;

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

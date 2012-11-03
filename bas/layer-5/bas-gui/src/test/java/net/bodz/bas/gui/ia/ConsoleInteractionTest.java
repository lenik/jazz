package net.bodz.bas.gui.ia;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.gui.dialog.ConsoleDialog;
import net.bodz.bas.model.IExecutableX;

public class ConsoleInteractionTest
        extends Assert {

    @Test
    public void testTryBlock()
            throws Exception {
        ConsoleDialog ci = ConsoleDialog.stderr;
        ci.tryBlock(new IExecutableX<Exception>() {
            @Override
            public void execute()
                    throws Exception {
                throw new ExpectedException();
            }
        });
    }

}

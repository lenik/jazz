package net.bodz.bas.gui.ia;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.gui.dialog.ConsoleDialogs;
import net.bodz.bas.model.IExecutableX;

public class ConsoleDialogTest
        extends Assert {

    @Test
    public void testTryBlock()
            throws Exception {
        ConsoleDialogs dialogs = ConsoleDialogs.stderr;
        dialogs.tryBlock(new IExecutableX<Exception>() {
            @Override
            public void execute()
                    throws Exception {
                throw new ExpectedException();
            }
        });
    }

}

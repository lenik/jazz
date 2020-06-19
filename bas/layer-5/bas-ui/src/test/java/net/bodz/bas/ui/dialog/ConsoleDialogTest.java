package net.bodz.bas.ui.dialog;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import net.bodz.bas.err.ExpectedException;
import net.bodz.bas.fn.IExecutableX;

public class ConsoleDialogTest
        extends Assert {

    @Ignore
    @Test
    public void tryBlock1()
            throws Exception {
        ConsoleDialogs dialogs = ConsoleDialogs.stderr;
        dialogs.tryBlock(new IExecutableX<Exception>() {
            @Override
            public void execute()
                    throws Exception {
                throw new ExpectedException("some error occurs.");
            }
        });
    }

}

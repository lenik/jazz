package net.bodz.swt.gui;

import net.bodz.bas.lang.RunnableThrows;
import net.bodz.bas.lang.err.ExpectedException;

import org.junit.Test;

public class DialogUIConstructsTest {

    @Test
    public void testTryBlock() throws Exception {
        DialogUIConstructs ui = new DialogUIConstructs();
        int exit = ui.tryBlock(new RunnableThrows<Exception>() {
            @Override
            public void run() throws Exception {
                throw new ExpectedException();
            }
        });
        System.out.println("Exit: " + exit);
    }

}

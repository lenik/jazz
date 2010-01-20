package net.bodz.bas.sio;

import org.junit.Test;

public class PrintStreamCharOutTest {

    @Test
    public void testStdout()
            throws Exception {
        ILineCharOut out = CharOuts.stdout;
        out.println("line1");
        out.println("line2");
    }

}

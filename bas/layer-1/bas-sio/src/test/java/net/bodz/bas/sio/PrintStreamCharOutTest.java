package net.bodz.bas.sio;

import org.junit.Test;

public class PrintStreamCharOutTest {

    @Test
    public void testStdout()
            throws Exception {
        ILineCharOut out = SystemIO.cout;
        out.println("line1");
        out.println("line2");
    }

}

package net.bodz.bas.sio;

import org.junit.Test;

public class PrintStreamCharOutTest {

    @Test
    public void testStdout()
            throws Exception {
        CharOut out = CharOut.stdout;
        out.println("line1");
        out.println("line2");
    }

}

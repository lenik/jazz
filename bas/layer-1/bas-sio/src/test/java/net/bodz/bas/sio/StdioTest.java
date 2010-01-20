package net.bodz.bas.sio;

import org.junit.Test;

public class StdioTest {

    @Test
    public void testCout()
            throws Exception {
        ILineCharOut out = Stdio.cout;
        out.println("line1");
        out.println("line2");
    }

}

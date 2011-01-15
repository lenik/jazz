package net.bodz.bas.sio;

import org.junit.Assert;
import org.junit.Test;

public class StdioTest
        extends Assert {

    @Test
    public void testCout()
            throws Exception {
        IPrintOut out = Stdio.cout;
        out.println("line1");
        out.println("line2");
    }

}

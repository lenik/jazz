package net.bodz.bas.sio;

import junit.framework.TestCase;

import org.junit.Test;

public class StdioTest
        extends TestCase {

    @Test
    public void testCout()
            throws Exception {
        IPrintCharOut out = Stdio.cout;
        out.println("line1");
        out.println("line2");
    }

}

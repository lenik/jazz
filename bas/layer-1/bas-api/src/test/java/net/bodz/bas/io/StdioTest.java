package net.bodz.bas.io;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;

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

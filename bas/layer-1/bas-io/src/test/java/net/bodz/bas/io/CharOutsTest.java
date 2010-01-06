package net.bodz.bas.io;

import net.bodz.bas.io.out.CharOut;
import net.bodz.bas.io.out.CharOuts;

import org.junit.Test;

public class CharOutsTest {

    @Test
    public void testStdout() throws Exception {
        CharOut out = CharOuts.stdout;
        out.println("line1"); //$NON-NLS-1$
        out.println("line2"); //$NON-NLS-1$
    }

}

package net.bodz.bas.io;

import org.junit.Test;

public class CharOutsTest {

    @Test
    public void testStdout() throws Exception {
        CharOut out = CharOuts.stdout;
        out.println("line1");
        out.println("line2");
    }

}

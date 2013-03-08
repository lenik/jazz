package net.bodz.bas.shell;

import org.junit.Assert;
import org.junit.Test;

public class MainTest
        extends Assert {

    @Test
    public void runMainClass()
            throws Exception {
        Program1.setArg0(null);
        Main.main(Program1.class.getName(), "test arg");
        assertEquals("test arg", Program1.getArg0());
    }

}

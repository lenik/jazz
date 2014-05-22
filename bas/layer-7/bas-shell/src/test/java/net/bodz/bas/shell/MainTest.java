package net.bodz.bas.shell;

import org.junit.Assert;
import org.junit.Test;

public class MainTest
        extends Assert {

    @Test
    public void runMainClass()
            throws Exception {
        StaticFieldSetter.setArg0(null);
        Main.main(StaticFieldSetter.class.getName(), "test arg");
        assertEquals("test arg", StaticFieldSetter.getArg0());
    }

}

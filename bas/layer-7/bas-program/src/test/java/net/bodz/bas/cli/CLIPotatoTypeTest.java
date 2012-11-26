package net.bodz.bas.cli;

import static org.junit.Assert.*;

import org.junit.Test;

import net.bodz.bas.cli.skel.BasicCLI;
import net.bodz.bas.potato.Potatoes;
import net.bodz.bas.potato.model.IType;

public class CLIPotatoTypeTest
        extends BasicCLI {

    /** @option */
    private String myName = "Lucy";

    /** @option hidden */
    private int yourAge = 13;

    /** @option */
    private boolean[] serial;

    /** @option */
    String hello(String t) {
        return "hello " + t;
    }

    @Override
    protected void mainImpl(String... args)
            throws Exception {
    }

    @Test
    public void test1()
            throws Exception {
        IType type = Potatoes.getType(getClass());
        // ScriptClass<Object> sclass = Scripts.getScriptClass(this);
        type.getProperty("myName").getValue(this);

        assertEquals("get myName", "Lucy", type.get(this, "myName"));
        assertEquals("get yourAge", 13, type.get(this, "yourAge"));

        type.set(this, "myName", "Linda");
        type.set(this, "yourAge", 17);
        assertEquals("set myName", "Linda", myName);
        assertEquals("set yourAge", 17, yourAge);

        accept("--serial", "--no-serial", "--no-serial");
        assertEquals("serials", 3, serial.length);
        assertTrue(serial[0]);
        assertFalse(serial[1]);
        assertFalse(serial[2]);

        Object ret = type.invoke(this, "hello", "Kate");
        assertEquals("hello()", "hello Kate", ret);
    }

}

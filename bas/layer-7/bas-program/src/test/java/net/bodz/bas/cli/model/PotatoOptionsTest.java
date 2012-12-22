package net.bodz.bas.cli.model;

import static org.junit.Assert.*;

import org.junit.Test;

import net.bodz.bas.potato.Potatoes;
import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.model.IType;

public class PotatoOptionsTest
        extends ArtifactObjectWithOptions {

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

    @Test
    public void testReceive()
            throws Exception {
        IType type = Potatoes.getType(getClass());

        for (IProperty property : type.getProperties())
            System.out.println("prop: " + property + ": " + property.getClass());

        type.getProperty("myName").getValue(this);

        assertEquals("get myName", "Lucy", type.get(this, "myName"));
        assertEquals("get yourAge", 13, type.get(this, "yourAge"));

        type.set(this, "myName", "Linda");
        type.set(this, "yourAge", 17);
        assertEquals("set myName", "Linda", myName);
        assertEquals("set yourAge", 17, yourAge);

        receive("--serial", "--no-serial", "--no-serial");
        assertEquals("serials", 3, serial.length);
        assertTrue(serial[0]);
        assertFalse(serial[1]);
        assertFalse(serial[2]);

        Object ret = type.invoke(this, "hello", "Kate");
        assertEquals("hello()", "hello Kate", ret);
    }

}

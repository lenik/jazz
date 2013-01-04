package net.bodz.bas.program.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import net.bodz.bas.potato.PotatoLoader;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.program.skel.CLISyntaxException;

public class ArtifactObjectWithOptionsTest
        extends ArtifactObjectWithOptions {

    /**
     * My name.
     * 
     * @option
     */
    private String myName = "Lucy";

    /**
     * A hidden option.
     * 
     * @option hidden
     */
    int yourAge = 13;

    /**
     * Serial bits.
     * 
     * @option
     */
    protected boolean[] serial;

    /**
     * The name property.
     * 
     * @option
     */
    public String getTheName() {
        return myName;
    }

    public void setTheName(String theName) {
        myName = theName;
    }

    /**
     * Show greeting message.
     * 
     * @option
     */
    String hello(String t) {
        return "hello " + t;
    }

    /**
     * The first feature.
     * 
     * @option -a
     */
    boolean feature1;

    /**
     * The second feature.
     * 
     * @option -b
     */
    boolean feature2;

    /**
     * The third feature.
     * 
     * @option -c
     */
    boolean feature3;

    @Ignore
    @Test
    public void testGetProperties() {
        IType type = PotatoLoader.getType(getClass());

        for (IProperty property : type.getProperties())
            System.out.println("prop: " + property + ": " + property.getClass());
    }

    @Test
    public void testSetProperties()
            throws Exception {
        IType type = PotatoLoader.getType(getClass());

        type.getProperty("myName").getValue(this);

        assertEquals("Lucy", type.get(this, "myName"));
        assertEquals(13, type.get(this, "yourAge"));

        type.set(this, "myName", "Linda");
        type.set(this, "yourAge", 17);
        assertEquals("Linda", myName);
        assertEquals(17, yourAge);
    }

    @Test
    public void receivePrivateVals()
            throws CLISyntaxException {
        assertEquals("Lucy", myName);
        assertEquals(13, yourAge);

        receive("--my-name", "Lily");
        assertEquals("Lily", myName);

        receive("--your-age=17", "--your-age=18");
        assertEquals(18, yourAge);
    }

    @Test
    public void receiveSimpleSwitches()
            throws CLISyntaxException {
        assertFalse(feature1);
        assertFalse(feature2);
        assertFalse(feature3);
        receive("-abb");
        assertTrue(feature1);
        assertTrue(feature2);
        assertFalse(feature3);
    }

    @Test
    public void receiveSwitches()
            throws CLISyntaxException {
        receive("--serial", "--no-serial", "--no-serial");
        assertEquals("serials", 3, serial.length);
        assertTrue(serial[0]);
        assertFalse(serial[1]);
        assertFalse(serial[2]);

// Object ret = type.invoke(this, "hello", "Kate");
// assertEquals("hello()", "hello Kate", ret);
    }

}

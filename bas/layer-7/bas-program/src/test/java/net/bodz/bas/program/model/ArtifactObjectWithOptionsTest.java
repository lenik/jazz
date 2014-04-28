package net.bodz.bas.program.model;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import net.bodz.bas.potato.PotatoTypes;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;

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
    String hello(String myNameBeingUsed) {
        this.myName = myNameBeingUsed;
        return "Yes, this is " + myNameBeingUsed;
    }

    /**
     * Show greeting message.
     * 
     * @option --hello2
     */
    String hello(String myFirstName, String myLastName) {
        this.myName = myFirstName + " " + myLastName;
        return "Hey, I'm " + myName;
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
        IType type = PotatoTypes.getInstance().forClass(getClass());

        for (IProperty property : type.getProperties())
            System.out.println("prop: " + property + ": " + property.getClass());
    }

    @Test
    public void testSetProperties()
            throws Exception {
        IType type = PotatoTypes.getInstance().forClass(getClass());

        type.getProperty("myName").getValue(this);

        assertEquals("Lucy", type.get(this, "myName"));
        assertEquals(13, type.get(this, "yourAge"));

        type.set(this, "myName", "Linda");
        type.set(this, "yourAge", 17);
        assertEquals("Linda", myName);
        assertEquals(17, yourAge);
    }

    @Test
    public void testInvoke()
            throws Exception {
        IType type = PotatoTypes.getInstance().forClass(getClass());
        Object retval = type.invoke(this, "hello", "Kate");
        assertEquals("Yes, this is Kate", retval);
    }

    @Test
    public void receivePrivateVals()
            throws Exception {
        assertEquals("Lucy", myName);
        assertEquals(13, yourAge);

        receive("--my-name", "Lily");
        assertEquals("Lily", myName);

        receive("--your-age=17", "--your-age=18");
        assertEquals(18, yourAge);
    }

    @Test
    public void receiveSimpleSwitches()
            throws Exception {
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
            throws Exception {
        receive("--serial", "--no-serial", "--no-serial");
        assertEquals("serials", 3, serial.length);
        assertTrue(serial[0]);
        assertFalse(serial[1]);
        assertFalse(serial[2]);
    }

    @Test
    public void receiveInvocation()
            throws Exception {
        assertEquals("Lucy", myName);
        receive("--hello", "Simpson");
        assertEquals("Simpson", myName);
    }

    @Test
    public void receiveInvocationOverload()
            throws Exception {
        assertEquals("Lucy", myName);
        receive("--hello2", "Bart", "Simpson");
        assertEquals("Bart Simpson", myName);
    }

}

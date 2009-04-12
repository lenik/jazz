package net.bodz.bas.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.lang.script.ScriptClass;
import net.bodz.bas.lang.script.Scripts;

import org.junit.Test;

public class CLIScriptClassTest extends BasicCLI {

    @Option
    private String    myName  = "Lucy"; //$NON-NLS-1$

    @Option(hidden = true)
    private int       yourAge = 13;

    @Option
    private boolean[] serial;

    @Option
    String hello(String t) {
        return "hello " + t; //$NON-NLS-1$
    }

    @Test
    public void test1() throws Exception {
        ScriptClass<Object> sclass = Scripts.getScriptClass(this);

        assertEquals("get myName", "Lucy", sclass.get(this, "myName")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertEquals("get yourAge", 13, sclass.get(this, "yourAge")); //$NON-NLS-1$ //$NON-NLS-2$

        sclass.set(this, "myName", "Linda"); //$NON-NLS-1$ //$NON-NLS-2$
        sclass.set(this, "yourAge", 17); //$NON-NLS-1$
        assertEquals("set myName", "Linda", myName); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("set yourAge", 17, yourAge); //$NON-NLS-1$

        addArguments("--serial", "--no-serial", "--no-serial"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertEquals("serials", 3, serial.length); //$NON-NLS-1$
        assertTrue(serial[0]);
        assertFalse(serial[1]);
        assertFalse(serial[2]);

        Object ret = sclass.invoke(this, "hello", "Kate"); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("hello()", "hello Kate", ret); //$NON-NLS-1$ //$NON-NLS-2$
    }

}

package net.bodz.bas.cli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.bodz.bas.meta.program.Option;

import org.junit.Test;

public class CLIScriptClassTest extends BasicCLI {

    @Option
    private String myName = "Lucy"; 

    @Option(hidden = true)
    private int yourAge = 13;

    @Option
    private boolean[] serial;

    @Option
    String hello(String t) {
        return "hello " + t; 
    }

    @Test
    public void test1() throws Exception {
        ScriptClass<Object> sclass = Scripts.getScriptClass(this);

        assertEquals("get myName", "Lucy", sclass.get(this, "myName"));   
        assertEquals("get yourAge", 13, sclass.get(this, "yourAge"));  

        sclass.set(this, "myName", "Linda");  
        sclass.set(this, "yourAge", 17); 
        assertEquals("set myName", "Linda", myName);  
        assertEquals("set yourAge", 17, yourAge); 

        addArguments("--serial", "--no-serial", "--no-serial");   
        assertEquals("serials", 3, serial.length); 
        assertTrue(serial[0]);
        assertFalse(serial[1]);
        assertFalse(serial[2]);

        Object ret = sclass.invoke(this, "hello", "Kate");  
        assertEquals("hello()", "hello Kate", ret);  
    }

}

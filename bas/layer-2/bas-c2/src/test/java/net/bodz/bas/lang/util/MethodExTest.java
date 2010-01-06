package net.bodz.bas.lang.util;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.exceptions.OutOfDomainException;

import org.junit.Test;

@Deprecated
public class MethodExTest {

    static final MethodEx _process;
    static {
        _process = new MethodEx(publicMethods(MethodExTest.class, "process"), 
                Object.class);
    }

    public Object process(Object in) {
        return _process.invoke(this, in);
    }

    public int process(int in) {
        return 2 * in;
    }

    public double process(double in) {
        return Math.sqrt(in);
    }

    public int process(Number in) {
        return 100 + in.intValue();
    }

    @Test
    public void testTypes() {
        Object forInt = process((Object) 44);
        assertEquals("forInt", 88, forInt); 

        Object forDouble = process((Object) 100.0);
        assertEquals("forDouble", 10.0, forDouble); 

        Object forNumber = process((Object) (byte) 10);
        assertEquals("forNumber", 110, forNumber); 
    }

    @Test(expected = OutOfDomainException.class)
    public void testInvalidType() {
        process((Object) "string"); 
    }

}

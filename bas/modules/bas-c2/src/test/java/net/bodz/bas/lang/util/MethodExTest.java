package net.bodz.bas.lang.util;

import static net.bodz.bas.commons.scripting.util.Members.publicMethods;
import static org.junit.Assert.assertEquals;
import net.bodz.bas.commons.exceptions.OutOfDomainException;
import net.bodz.bas.commons.scripting.util.MethodEx;

import org.junit.Test;

@Deprecated
public class MethodExTest {

    static final MethodEx _process;
    static {
        _process = new MethodEx(publicMethods(MethodExTest.class, "process"), //$NON-NLS-1$
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
        assertEquals("forInt", 88, forInt); //$NON-NLS-1$

        Object forDouble = process((Object) 100.0);
        assertEquals("forDouble", 10.0, forDouble); //$NON-NLS-1$

        Object forNumber = process((Object) (byte) 10);
        assertEquals("forNumber", 110, forNumber); //$NON-NLS-1$
    }

    @Test(expected = OutOfDomainException.class)
    public void testInvalidType() {
        process((Object) "string"); //$NON-NLS-1$
    }

}

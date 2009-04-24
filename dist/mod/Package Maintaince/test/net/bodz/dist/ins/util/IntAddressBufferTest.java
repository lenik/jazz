package net.bodz.dist.ins.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IntAddressBufferTest {

    @Test
    public void test() throws Exception {
        IntAddressBuffer buf = new IntAddressBuffer();
        assertEquals(".", buf.toString()); //$NON-NLS-1$
        buf.push(1);
        assertEquals("1", buf.toString()); //$NON-NLS-1$
        buf.push(2);
        buf.push(3);
        assertEquals("1.2.3", buf.toString()); //$NON-NLS-1$
        buf.push(4);
        buf.push(5);
        buf.push(6);
        buf.push(7);
        buf.push(8);
        buf.push(9);
        buf.push(10);
        assertEquals("1.2.3.4.5.6.7.8.9.10", buf.toString()); //$NON-NLS-1$
        buf.pop();
        buf.pop();
        assertEquals("1.2.3.4.5.6.7.8", buf.toString()); //$NON-NLS-1$
        buf.set(100);
        assertEquals("1.2.3.4.5.6.7.100", buf.toString()); //$NON-NLS-1$
        buf.add(-10);
        assertEquals("1.2.3.4.5.6.7.90", buf.toString()); //$NON-NLS-1$
        buf.or(1);
        assertEquals("1.2.3.4.5.6.7.91", buf.toString()); //$NON-NLS-1$
    }

}

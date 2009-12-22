package net.bodz.bas.types.util;

import static net.bodz.bas.commons.collection.util.ArrayOps.Bytes;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayOpsTest {

    @Test
    public void testIndexOf() {
        byte[] b = { 10, 100, 2, 5, 7, 0, 2, 3, };

        int i;
        assertEquals("index init", 2, i = Bytes.indexOf(b, (byte) 2)); //$NON-NLS-1$
        assertEquals("index cont", 6, i = Bytes.indexOf(b, (byte) 2, i + 1)); //$NON-NLS-1$
        assertEquals("index last", -1, i = Bytes.indexOf(b, (byte) 2, i + 1)); //$NON-NLS-1$

        assertEquals("rev init", 6, i = Bytes.lastIndexOf(b, (byte) 2)); //$NON-NLS-1$
        assertEquals("rev cont", 2, i = Bytes.lastIndexOf(b, (byte) 2, i - 1)); //$NON-NLS-1$
        assertEquals("rev last", -1, i = Bytes.lastIndexOf(b, (byte) 2, i - 1)); //$NON-NLS-1$
    }

}

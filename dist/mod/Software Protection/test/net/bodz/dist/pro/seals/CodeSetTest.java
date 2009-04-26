package net.bodz.dist.pro.seals;

import static org.junit.Assert.assertEquals;

import net.bodz.dist.pro.seals.CodeSet;

import org.junit.Test;

public class CodeSetTest {

    @Test
    public void testEncodeInt() throws Exception {
        assertEquals("R", CodeSet.encode(0)); //$NON-NLS-1$
        assertEquals("A", CodeSet.encode(10)); //$NON-NLS-1$
        assertEquals("34", CodeSet.encode(100)); //$NON-NLS-1$
        assertEquals("Z8", CodeSet.encode(1000)); //$NON-NLS-1$
        assertEquals("9QH", CodeSet.encode(10000)); //$NON-NLS-1$
        assertEquals("31M0", CodeSet.encode(100000)); //$NON-NLS-1$
        assertEquals("YHJ0", CodeSet.encode(1000000)); //$NON-NLS-1$
        assertEquals("9I5L0", CodeSet.encode(10000000)); //$NON-NLS-1$
        assertEquals("2ZBQ80", CodeSet.encode(100000000)); //$NON-NLS-1$
        assertEquals("XSMJH0", CodeSet.encode(1000000000)); //$NON-NLS-1$
        assertEquals("3ZZZZZZ", CodeSet.encode(-1)); //$NON-NLS-1$
    }

    @Test
    public void testEncodeLong() throws Exception {
        assertEquals("9A0PS00", CodeSet.encode(10000000000L)); //$NON-NLS-1$
        assertEquals("2X47DT00", CodeSet.encode(100000000000L)); //$NON-NLS-1$
        assertEquals("X3AAA400", CodeSet.encode(1000000000000L)); //$NON-NLS-1$
        assertEquals("931775800", CodeSet.encode(10000000000000L)); //$NON-NLS-1$
        assertEquals("2TYC87LH00", CodeSet.encode(100000000000000L)); //$NON-NLS-1$
        assertEquals("VDGTJCD000", CodeSet.encode(1000000000000000L)); //$NON-NLS-1$
    }

    @Test
    public void testDecodeInt() throws Exception {
        assertEquals(0, CodeSet.decode("R")); //$NON-NLS-1$
        assertEquals(10, CodeSet.decode("A")); //$NON-NLS-1$
        assertEquals(100, CodeSet.decode("34")); //$NON-NLS-1$
        assertEquals(1000, CodeSet.decode("Z8")); //$NON-NLS-1$
        assertEquals(10000, CodeSet.decode("9QH")); //$NON-NLS-1$
        assertEquals(100000, CodeSet.decode("31M0")); //$NON-NLS-1$
        assertEquals(1000000, CodeSet.decode("YHJ0")); //$NON-NLS-1$
        assertEquals(10000000, CodeSet.decode("9I5L0")); //$NON-NLS-1$
        assertEquals(10000000, CodeSet.decode("9I5LW")); //$NON-NLS-1$
        assertEquals(100000000, CodeSet.decode("2ZBQ80")); //$NON-NLS-1$
        assertEquals(1000000000, CodeSet.decode("XSMJH0")); //$NON-NLS-1$
        assertEquals(-1, CodeSet.decode("3ZZZZZZ")); //$NON-NLS-1$
        // space, illegal chars..
        assertEquals(1000000000, CodeSet.decode("XS MJ  H0")); //$NON-NLS-1$
        assertEquals(1000000000, CodeSet.decode(" X  S-@^$M  J H  0")); //$NON-NLS-1$
    }

    @Test
    public void testDecodeLong() throws Exception {
        assertEquals(10000000000L, CodeSet.decodeLong("9A0PS00")); //$NON-NLS-1$
        assertEquals(100000000000L, CodeSet.decodeLong("2X47DT00")); //$NON-NLS-1$
        assertEquals(1000000000000L, CodeSet.decodeLong("X3AAA400")); //$NON-NLS-1$
        assertEquals(10000000000000L, CodeSet.decodeLong("931775800")); //$NON-NLS-1$
        assertEquals(100000000000000L, CodeSet.decodeLong("2TYC87LH00")); //$NON-NLS-1$
        assertEquals(1000000000000000L, CodeSet.decodeLong("VDGTJCD000")); //$NON-NLS-1$
    }

}

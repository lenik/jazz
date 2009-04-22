package net.bodz.dist.seals;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CodeSetTest {

    @Test
    public void testEncodeInt() throws Exception {
        assertEquals("R", CodeSet.encode(0));
        assertEquals("A", CodeSet.encode(10));
        assertEquals("34", CodeSet.encode(100));
        assertEquals("Z8", CodeSet.encode(1000));
        assertEquals("9QH", CodeSet.encode(10000));
        assertEquals("31M0", CodeSet.encode(100000));
        assertEquals("YHJ0", CodeSet.encode(1000000));
        assertEquals("9I5L0", CodeSet.encode(10000000));
        assertEquals("2ZBQ80", CodeSet.encode(100000000));
        assertEquals("XSMJH0", CodeSet.encode(1000000000));
        assertEquals("3ZZZZZZ", CodeSet.encode(-1));
    }

    @Test
    public void testEncodeLong() throws Exception {
        assertEquals("9A0PS00", CodeSet.encode(10000000000L));
        assertEquals("2X47DT00", CodeSet.encode(100000000000L));
        assertEquals("X3AAA400", CodeSet.encode(1000000000000L));
        assertEquals("931775800", CodeSet.encode(10000000000000L));
        assertEquals("2TYC87LH00", CodeSet.encode(100000000000000L));
        assertEquals("VDGTJCD000", CodeSet.encode(1000000000000000L));
    }

    @Test
    public void testDecodeInt() throws Exception {
        assertEquals(0, CodeSet.decode("R"));
        assertEquals(10, CodeSet.decode("A"));
        assertEquals(100, CodeSet.decode("34"));
        assertEquals(1000, CodeSet.decode("Z8"));
        assertEquals(10000, CodeSet.decode("9QH"));
        assertEquals(100000, CodeSet.decode("31M0"));
        assertEquals(1000000, CodeSet.decode("YHJ0"));
        assertEquals(10000000, CodeSet.decode("9I5L0"));
        assertEquals(10000000, CodeSet.decode("9I5LW"));
        assertEquals(100000000, CodeSet.decode("2ZBQ80"));
        assertEquals(1000000000, CodeSet.decode("XSMJH0"));
        assertEquals(-1, CodeSet.decode("3ZZZZZZ"));
        // space, illegal chars..
        assertEquals(1000000000, CodeSet.decode("XS MJ  H0"));
        assertEquals(1000000000, CodeSet.decode(" X  S-@^$M  J H  0"));
    }

    @Test
    public void testDecodeLong() throws Exception {
        assertEquals(10000000000L, CodeSet.decodeLong("9A0PS00"));
        assertEquals(100000000000L, CodeSet.decodeLong("2X47DT00"));
        assertEquals(1000000000000L, CodeSet.decodeLong("X3AAA400"));
        assertEquals(10000000000000L, CodeSet.decodeLong("931775800"));
        assertEquals(100000000000000L, CodeSet.decodeLong("2TYC87LH00"));
        assertEquals(1000000000000000L, CodeSet.decodeLong("VDGTJCD000"));
    }

}

package net.bodz.bas.io.data;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;

public class DataInImplLETest
        extends AbstractDataInTest {

    @Override
    protected IDataIn create(IByteIn byteIn) {
        return new DataInImplLE(byteIn);
    }

    @Test
    public void testIsBigEndian() {
        IDataIn in = getDataIn(seqBytes);
        assertFalse(in.isBigEndian());
    }

    @Test
    public void testReadWord()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals(0x2211, in.readWord());
        assertEquals(0x4433, in.readWord());
        assertEquals(0x6655, in.readWord());
        assertEquals((short) 0x8877, in.readWord());
    }

    @Test
    public void testReadDword()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals(0x44332211, in.readDword());
        assertEquals(0x88776655, in.readDword());
        assertEquals(0xccbbaa99, in.readDword());
        assertEquals(0x00ffeedd, in.readDword());
    }

    @Test
    public void testReadQword()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals(0x8877665544332211L, in.readQword());
        assertEquals(0x00FFEEDDCCBBAA99L, in.readQword());
    }

    @Test
    public void testReadFloat()
            throws IOException {
        IDataIn in = getDataIn(floatBytesLE);
        assertEquals(12.345f, in.readFloat(), 0);
        assertEquals(Float.NaN, in.readFloat(), 0);
        assertEquals(Float.POSITIVE_INFINITY, in.readFloat(), 0);
    }

    @Test
    public void testReadDouble()
            throws IOException {
        IDataIn in = getDataIn(doubleBytesLE);
        assertEquals(12.345, in.readDouble(), 0);
        assertEquals(Double.NaN, in.readDouble(), 0);
        assertEquals(Double.POSITIVE_INFINITY, in.readDouble(), 0);
    }

}

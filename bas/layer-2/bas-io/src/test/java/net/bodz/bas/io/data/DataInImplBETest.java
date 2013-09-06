package net.bodz.bas.io.data;

import java.io.IOException;

import org.junit.Test;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IDataIn;

public class DataInImplBETest
        extends AbstractDataInTest {

    @Override
    protected IDataIn create(IByteIn byteIn) {
        return new DataInImplBE(byteIn);
    }

    @Test
    public void testIsBigEndian() {
        IDataIn in = getDataIn(seqBytes);
        assertTrue(in.isBigEndian());
    }

    @Test
    public void testReadWord()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals(0x1122, in.readWord());
        assertEquals(0x3344, in.readWord());
        assertEquals(0x5566, in.readWord());
        assertEquals(0x7788, in.readWord());
    }

    @Test
    public void testReadDword()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals(0x11223344, in.readDword());
        assertEquals(0x55667788, in.readDword());
        assertEquals(0x99aabbcc, in.readDword());
        assertEquals(0xddeeff00, in.readDword());
    }

    @Test
    public void testReadQword()
            throws IOException {
        IDataIn in = getDataIn(seqBytes);
        assertEquals(0x1122334455667788L, in.readQword());
        assertEquals(0x99aabbccddeeff00L, in.readQword());
    }

    @Test
    public void testReadFloat()
            throws IOException {
        IDataIn in = getDataIn(floatBytesBE);
        assertEquals(12.345f, in.readFloat(), 0);
        assertEquals(Float.NaN, in.readFloat(), 0);
        assertEquals(Float.POSITIVE_INFINITY, in.readFloat(), 0);
    }

    @Test
    public void testReadDouble()
            throws IOException {
        IDataIn in = getDataIn(doubleBytesBE);
        assertEquals(12.345, in.readDouble(), 0);
        assertEquals(Double.NaN, in.readDouble(), 0);
        assertEquals(Double.POSITIVE_INFINITY, in.readDouble(), 0);
    }

}

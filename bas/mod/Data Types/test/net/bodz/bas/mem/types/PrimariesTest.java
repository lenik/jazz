package net.bodz.bas.mem.types;

import static net.bodz.bas.text.encodings.Encodings.HEX;
import static net.bodz.bas.types.util.ArrayOps.Bytes;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import net.bodz.bas.mem.ArrayMemory;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.Type;

import org.junit.Test;

public class PrimariesTest {

    Random rands = new Random();

    void test(Class<? extends Type> typeClass, Object val, String memHex) throws Exception {
        String tnam = typeClass.getSimpleName();
        byte[] mem0 = HEX.decode(memHex);
        byte[] memv = Bytes.copyOf(mem0);
        Memory mem = new ArrayMemory(memv);
        Type type;
        try {
            type = typeClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Bytes.fill(memv, rands);
        type.put(mem, 0, val);
        String putHex = HEX.encode(memv);
        System.out.println("hex of " + tnam + " " + val + ": " + putHex); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertArrayEquals("put " + tnam, mem0, memv); //$NON-NLS-1$

        Bytes.copy(mem0, 0, memv, 0, mem0.length);
        Object mem2val = type.get(mem, 0);
        assertEquals("get " + tnam, val, mem2val); //$NON-NLS-1$
    }

    @Test
    public void testInts() throws Exception {
        test(Int8.class, (byte) 0x81, "81"); //$NON-NLS-1$
        test(Int16LE.class, (short) 0x8182, "82 81"); //$NON-NLS-1$
        test(Int16BE.class, (short) 0x8182, "81 82"); //$NON-NLS-1$
        test(Int32LE.class, 0x81828384, "84 83 82 81"); //$NON-NLS-1$
        test(Int32BE.class, 0x81828384, "81 82 83 84"); //$NON-NLS-1$
        test(Int64LE.class, 0x8182838485868788L, "88 87 86 85 84 83 82 81"); //$NON-NLS-1$
        test(Int64BE.class, 0x8182838485868788L, "81 82 83 84 85 86 87 88"); //$NON-NLS-1$
    }

    @Test
    public void testFloats() throws Exception {
        test(Ieee754FloatLE.class, Float.MIN_VALUE, "01 00 00 00"); //$NON-NLS-1$
        test(Ieee754FloatLE.class, Float.MAX_VALUE, "ff ff 7f 7f"); //$NON-NLS-1$
        test(Ieee754FloatLE.class, Float.NaN, "00 00 c0 7f"); //$NON-NLS-1$
        test(Ieee754FloatLE.class, 1.0f, "00 00 80 3f"); //$NON-NLS-1$

        test(Ieee754FloatBE.class, Float.MIN_VALUE, "00 00 00 01"); //$NON-NLS-1$
        test(Ieee754FloatBE.class, Float.MAX_VALUE, "7f 7f Ff Ff"); //$NON-NLS-1$
        test(Ieee754FloatBE.class, Float.NaN, "7F C0 00 00"); //$NON-NLS-1$
        test(Ieee754FloatBE.class, 1.0f, "3F 80 00 00"); //$NON-NLS-1$

        test(Ieee754DoubleLE.class, Double.MIN_VALUE, "01 00 00 00 00 00 00 00"); //$NON-NLS-1$
        test(Ieee754DoubleLE.class, Double.MAX_VALUE, "ff ff ff ff ff ff ef 7f"); //$NON-NLS-1$
        test(Ieee754DoubleLE.class, Double.NaN, "00 00 00 00 00 00 f8 7f"); //$NON-NLS-1$
        test(Ieee754DoubleLE.class, 1.0, "00 00 00 00 00 00 f0 3f"); //$NON-NLS-1$

        test(Ieee754DoubleBE.class, Double.MIN_VALUE, "00 00 00 00 00 00 00 01"); //$NON-NLS-1$
        test(Ieee754DoubleBE.class, Double.MAX_VALUE, "7f ef ff ff ff ff ff ff"); //$NON-NLS-1$
        test(Ieee754DoubleBE.class, Double.NaN, "7f f8 00 00 00 00 00 00"); //$NON-NLS-1$
        test(Ieee754DoubleBE.class, 1.0, "3f f0 00 00 00 00 00 00"); //$NON-NLS-1$
    }

}

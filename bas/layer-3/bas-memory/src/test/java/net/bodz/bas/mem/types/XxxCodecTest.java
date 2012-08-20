package net.bodz.bas.mem.types;

import java.util.Arrays;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.mem.ArrayMemory;
import net.bodz.bas.mem.Memory;
import net.bodz.bas.mem.Type;
import net.bodz.bas.text.codec.builtin.HexCodec;

public class XxxCodecTest
        extends Assert {

    Random random = new Random();
    HexCodec hexCodec = HexCodec.getInstance();

    void test(Class<? extends Type> typeClass, Object val, String memHex)
            throws Exception {
        String tnam = typeClass.getSimpleName();
        byte[] mem0 = hexCodec.decode(memHex);
        byte[] memv = Arrays.copyOf(mem0, mem0.length);
        Memory mem = new ArrayMemory(memv);
        Type type;
        try {
            type = typeClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        random.nextBytes(memv);

        type.put(mem, 0, val);
        String putHex = hexCodec.encode(memv);
        System.out.println("hex of " + tnam + " " + val + ": " + putHex);
        assertArrayEquals("put " + tnam, mem0, memv);

        System.arraycopy(mem0, 0, memv, 0, mem0.length);
        Object mem2val = type.get(mem, 0);
        assertEquals("get " + tnam, val, mem2val);
    }

    @Test
    public void testInts()
            throws Exception {
        test(Int8.class, (byte) 0x81, "81");
        test(Int16LE.class, (short) 0x8182, "82 81");
        test(Int16BE.class, (short) 0x8182, "81 82");
        test(Int32LE.class, 0x81828384, "84 83 82 81");
        test(Int32BE.class, 0x81828384, "81 82 83 84");
        test(Int64LE.class, 0x8182838485868788L, "88 87 86 85 84 83 82 81");
        test(Int64BE.class, 0x8182838485868788L, "81 82 83 84 85 86 87 88");
    }

    @Test
    public void testFloats()
            throws Exception {
        test(Ieee754FloatLE.class, Float.MIN_VALUE, "01 00 00 00");
        test(Ieee754FloatLE.class, Float.MAX_VALUE, "ff ff 7f 7f");
        test(Ieee754FloatLE.class, Float.NaN, "00 00 c0 7f");
        test(Ieee754FloatLE.class, 1.0f, "00 00 80 3f");

        test(Ieee754FloatBE.class, Float.MIN_VALUE, "00 00 00 01");
        test(Ieee754FloatBE.class, Float.MAX_VALUE, "7f 7f Ff Ff");
        test(Ieee754FloatBE.class, Float.NaN, "7F C0 00 00");
        test(Ieee754FloatBE.class, 1.0f, "3F 80 00 00");

        test(Ieee754DoubleLE.class, Double.MIN_VALUE, "01 00 00 00 00 00 00 00");
        test(Ieee754DoubleLE.class, Double.MAX_VALUE, "ff ff ff ff ff ff ef 7f");
        test(Ieee754DoubleLE.class, Double.NaN, "00 00 00 00 00 00 f8 7f");
        test(Ieee754DoubleLE.class, 1.0, "00 00 00 00 00 00 f0 3f");

        test(Ieee754DoubleBE.class, Double.MIN_VALUE, "00 00 00 00 00 00 00 01");
        test(Ieee754DoubleBE.class, Double.MAX_VALUE, "7f ef ff ff ff ff ff ff");
        test(Ieee754DoubleBE.class, Double.NaN, "7f f8 00 00 00 00 00 00");
        test(Ieee754DoubleBE.class, 1.0, "3f f0 00 00 00 00 00 00");
    }

}

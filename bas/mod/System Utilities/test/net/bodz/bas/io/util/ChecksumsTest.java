package net.bodz.bas.io.util;

import static org.junit.Assert.assertEquals;

import java.util.zip.CRC32;

import net.bodz.bas.io.util.Checksums.CRC32pgp;

import org.junit.Test;

public class ChecksumsTest {

    public void testCRC32(byte[] data) {
        CRC32 zipc = new CRC32();
        CRC32pgp pgp = new CRC32pgp();
        long z, n;
        // by each
        for (int i = 0; i < data.length; i++) {
            int b = data[i] & 0xff;
            zipc.update(b);
            z = zipc.getValue();
            pgp.update(b);
            n = pgp.getValue();
            assertEquals("byte:" + i, z, n);
        }
        // by once
        zipc = new CRC32();
        pgp = new CRC32pgp();
        zipc.update(data);
        z = zipc.getValue();
        pgp.update(data);
        n = pgp.getValue();
        assertEquals(z, n);
    }

    @Test
    public void test1() {
        // byte[] k = { (byte) 0x04, (byte) 0xc1, (byte) 0x1d, (byte) 0xb7 };
        // byte[] k = { (byte) 0xb7, (byte) 0x1d, (byte) 0xc1, (byte) 0x04, };
        // testCRC32(k);
        testCRC32("\0".getBytes());
        testCRC32("123".getBytes());
        testCRC32("el".getBytes());
    }

}

package net.bodz.bas.data.util;

import java.util.zip.CRC32;

import net.bodz.bas.c.java.util.Arrays;
import net.bodz.bas.c.primitive.IntMath;

public class Crc32Core {

    private final int polynomial;
    private final boolean inverted;

    public final int[] table = new int[256];
    private final int[] reverseTable = new int[256];

    public Crc32Core(int polynomial, boolean inverted) {
        this.polynomial = polynomial;
        this.inverted = inverted;
        buildTable();
    }

    void buildTable() {
        for (int i = 0; i < 256; i++) {
            int cell = IntMath.reflect8(i) << 24;
            for (int j = 0; j < 8; j++) {
                boolean msb = (cell & 0x80000000) != 0;
                cell = (cell << 1) ^ (msb ? polynomial : 0);
            }
            cell = IntMath.reflect32(cell);
            table[i] = cell;
        }

        for (int i = 0; i < 256; i++) {
            int cell = table[i];
            int lsb = cell >>> 24;
            for (int r = 0; r < 256; r++)
                if ((r ^ lsb) == 0) {
                    reverseTable[r] = i;
                    break;
                }
        }
    }

    public int _update(int crc32, int byt) {
        int c = crc32;
        c = (c >>> 8) ^ table[(byt ^ c) & 0xff];
        return c;
    }

    public int update(int crc32, int byt) {
        int c = crc32;
        if (inverted)
            c = ~c;

        c = (c >>> 8) ^ table[byt ^ (c & 0xff)];

        if (inverted)
            c = ~c;

        return c;
    }

    public int update(int crc32, byte[] buf) {
        return update(crc32, buf, 0, buf.length);
    }

    public int update(int crc32, byte[] buf, int off, int len) {
        int c = crc32;
        if (inverted)
            c = ~c;

        for (int i = 0; i < len; i++)
            c = (c >>> 8) ^ table[buf[off++] ^ (c & 0xff)];

        if (inverted)
            c = ~c;

        return c;
    }

    public byte[] findReverse(int init, int expected) {
        int[] crcIdx = new int[4];

        int tmp = expected;
        if (inverted)
            tmp = ~tmp;
        for (int i = 3; i >= 0; i--) {
            int idx = reverseTable[tmp >>> 24];
            crcIdx[i] = idx;
            tmp = (tmp ^ table[idx]) << 8;
        }

        byte[] patch = new byte[4];
        int crc = init;
        if (inverted)
            crc = ~crc;

        for (int i = 0; i < 4; i++) {
            int idx = crcIdx[i];
            int byt = (crc ^ idx) & 0xff;
            patch[i] = (byte) byt;
            crc = (crc >>> 8) ^ table[byt ^ (crc & 0xff)];
        }

        return patch;
    }

    // 62 = 10+26+26
    static final char[] ntab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    public String findReverseAscii(int init, int expected) {
        CRC32 crc32 = new CRC32();
        for (int a = 0; a < 62; a++)
            L: for (int b = 0; b < 62; b++) {
                crc32.reset();
                crc32.update(ntab[a]);
                crc32.update(ntab[b]);

                byte[] patch = findReverse((int) crc32.getValue(), expected);
                for (int i = 0; i < patch.length; i++) {
                    int byt = patch[i] & 0xff;
                    if (!(byt >= '0' && byt <= '9' || byt >= 'A' && byt <= 'Z' || byt >= 'a' && byt <= 'z'))
                        continue L;
                }

                char chars[] = { ntab[a], ntab[b], //
                        (char) (patch[0] & 0xff), //
                        (char) (patch[1] & 0xff), //
                        (char) (patch[2] & 0xff), //
                        (char) (patch[3] & 0xff), };
                return new String(chars);
            }
        return null;
    }

    public CRC32 newCRC32(int crc32) {
        byte[] initiator = findReverse(0, crc32);
        CRC32 obj = new CRC32();
        obj.update(initiator);
        return obj;
    }

    public CRC32 clone(CRC32 crc32) {
        return newCRC32((int) crc32.getValue());
    }

    public void dumpTable() {
        System.out.println(Arrays.toHex(8, table));
    }

    public void dumpReverseTable() {
        byte[] bv = new byte[reverseTable.length];
        for (int i = 0; i < bv.length; i++)
            bv[i] = (byte) reverseTable[i];
        System.out.println(Arrays.toHex(16, bv));
    }

    public static final int DEFAULT_POLYNOMIAL = 0x04c11db7;
    static Crc32Core instance = new Crc32Core(DEFAULT_POLYNOMIAL, true);

    public static Crc32Core getInstance() {
        return instance;
    }

}

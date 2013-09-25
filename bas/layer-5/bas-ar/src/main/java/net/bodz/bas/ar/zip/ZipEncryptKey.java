package net.bodz.bas.ar.zip;

import net.bodz.bas.data.util.Crc32Core;
import net.bodz.bas.typer.std.ValidationException;

/**
 * Each encrypted file has an extra 12 bytes stored at the start of the data area defining the
 * encryption header for that file. The encryption header is originally set to random values, and
 * then itself encrypted, using three, 32-bit keys. The key values are initialized using the
 * supplied encryption password. After each byte is encrypted, the keys are then updated using
 * pseudo-random number generation techniques in combination with the same CRC-32 algorithm used in
 * PKZIP and described elsewhere in this document.
 */
public class ZipEncryptKey {

    private static final Crc32Core CORE = Crc32Core.getInstance();

    private int key0;
    private int key1;
    private int key2;

    public ZipEncryptKey() {
        this(0x12345678, 0x23456789, 0x34567890);
    }

    public ZipEncryptKey(int key0, int key1, int key2) {
        this.key0 = key0;
        this.key1 = key1;
        this.key2 = key2;
    }

    public ZipEncryptKey(String password) {
        this();
        char[] pv = password.toCharArray();
        for (char ch : pv)
            updateKeys(ch & 0xff);
    }

    @Override
    public ZipEncryptKey clone() {
        return new ZipEncryptKey(key0, key1, key2);
    }

    private void updateKeys(int b) {
        key0 = CORE._update(key0, b);
        key1 += (int) (key0 & 0xff);
        key1 = key1 * 134775813 + 1;
        key2 = CORE._update(key2, key1 >>> 24);
    }

    public void decrypt(byte[] buf) {
        decrypt(buf, 0, buf.length);
    }

    public void decrypt(byte[] buf, int off, int len) {
        ZipEncryptKey g = this; // clone();
        for (int i = 0; i < len; i++) {
            byte byt = (byte) (buf[off] ^ g.magicByte());
            buf[off++] = byt;
            g.updateKeys(byt & 0xff);
        }
    }

    public byte decrypt(byte b) {
        b ^= magicByte();
        updateKeys(b & 0xff);
        return b;
    }

    private byte magicByte() {
        short t = (short) (key2 | 2);
        return (byte) ((t * (t ^ 1)) >>> 8);
    }

    /**
     * After the header is decrypted, the last 1 or 2 bytes in Buffer should be the high-order
     * word/byte of the CRC for the file being decrypted, stored in Intel low-byte/high-byte order.
     * Versions of PKZIP prior to 2.0 used a 2 byte CRC check; a 1 byte CRC check is used on
     * versions after 2.0. This can be used to test if the password supplied is correct or not.
     */
    public void validateEH(byte[] ehText, ZipEntry entry)
            throws ValidationException {
        int off = ehText.length;

        int crc = entry.crc32;
        boolean crcMatched = true;

        int cb = 2;
        for (int i = 0; i < cb; i++) {
            int h = crc >>> 24;
            int actual = ehText[--off] & 0xff;
            if (actual != h) {
                crcMatched = false;
            }
            crc <<= 8;
        }

        if (!crcMatched) {
            if (!entry.isDDExisted())
                throw new ValidationException("The password did not match.");

            if (ehText[11] != (byte) ((entry.mtime >> 8) & 0xff))
                throw new ValidationException("The password did not match.");
        }
    }

    @Override
    public String toString() {
        return key0 + ", " + key1 + ", " + key2;
    }

}

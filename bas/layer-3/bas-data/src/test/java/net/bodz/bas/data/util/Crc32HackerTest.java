package net.bodz.bas.data.util;

import java.io.IOException;
import java.util.zip.CRC32;

import org.junit.Assert;

public class Crc32HackerTest
        extends Assert {

    public static void main(String[] args)
            throws IOException {
        Crc32Core hacker = Crc32Core.getInstance();

        CRC32 crc = new CRC32();

        String patchstr = hacker.findReverseAscii((int) crc.getValue(), 0x124836cf);
        byte[] patch = patchstr.getBytes();

        crc.update(patch);
        int actual = (int) crc.getValue();
        System.out.printf("%s => %x\n", patchstr, actual);

        Crc32Core noinv = new Crc32Core(Crc32Core.DEFAULT_POLYNOMIAL, false);
        actual = noinv.update(0, patch);
        System.out.printf("no-inv result: %x\n", actual);

    }

}

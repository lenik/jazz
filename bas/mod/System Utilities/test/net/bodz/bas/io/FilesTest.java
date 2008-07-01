package net.bodz.bas.io;

import org.junit.Test;

public class FilesTest {

    static {
        Files.blockSize = 4;
    }

    @Test
    public void testDiff() throws Throwable {
        byte[] src = "abcdefghijklmopqrstuvwxyz".getBytes();
        byte[] dst = "abcdefghijklmopqrst".getBytes();
        System.out.println(Files.diff_1(src, dst));
        src = "abcdefghijklmopqrst".getBytes();
        dst = "abcdefghijklmopqrst".getBytes();
        System.out.println(Files.diff_1(src, dst));
        src = "abcdefghijklmopqrst".getBytes();
        dst = "-bcdefghijklmopqrst".getBytes();
        System.out.println(Files.diff_1(src, dst));
        System.out.println(Files.diff_1(src, null));
    }

}

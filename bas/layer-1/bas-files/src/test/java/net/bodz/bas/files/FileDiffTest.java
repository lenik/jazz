package net.bodz.bas.files;

import junit.framework.TestCase;

import org.junit.Test;

public class FileDiffTest
        extends TestCase {

    @Test
    public void testDiff()
            throws Throwable {
        class D {
            void o(String pair, long expected)
                    throws Throwable {
                int vert = pair.indexOf('|');
                byte[] src = pair.substring(0, vert).getBytes();
                byte[] dst = pair.substring(vert + 1).getBytes();
                long actual = FileDiff.diff_1(src, dst);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("abcdefghijklmopqrstuvwxyz|abcdefghijklmopqrst", 19L);
        d.o("abcdefghijklmopqrst|abcdefghijklmopqrst", -1L);
        d.o("abcdefghijklmopqrst|-bcdefghijklmopqrst", 0L);
    }

}

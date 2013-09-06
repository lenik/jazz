package net.bodz.bas.c.java.io;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.io.res.builtin.ByteArrayResource;

public class FileDiffTest
        extends Assert {

    @Test
    public void testDiff()
            throws Throwable {
        class D {
            void o(String pair, long expected)
                    throws Throwable {
                int vert = pair.indexOf('|');

                byte[] srcBytes = pair.substring(0, vert).getBytes();
                byte[] dstBytes = pair.substring(vert + 1).getBytes();

                ByteArrayResource src = new ByteArrayResource(srcBytes);
                ByteArrayResource dst = new ByteArrayResource(dstBytes);

                long actual = FileDiff.findFirstDifferentByte(src, dst);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("abcdefghijklmopqrstuvwxyz|abcdefghijklmopqrst", 19L);
        d.o("abcdefghijklmopqrst|abcdefghijklmopqrst", -1L);
        d.o("abcdefghijklmopqrst|-bcdefghijklmopqrst", 0L);
    }

}

package net.bodz.bas.io.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class TellableInputStreamTest
        extends Assert {

    static byte[] hello = "hello, abcdefg".getBytes();

    @Test
    public void test1()
            throws IOException {
        TellableInputStream in = new TellableInputStream(new ByteArrayInputStream(hello));

        assertEquals(0, in.tell());

        in.read();
        in.read();
        assertEquals(2, in.tell());

        in.read(new byte[2]);
        assertEquals(4, in.tell());

        in.mark(3);
        in.read();
        in.read();

        in.reset();
        assertEquals(4, in.tell());
        in.read();

        in.reset();
        assertEquals(4, in.tell());

        if (true) {
            in.read();
            in.read();
            in.read();
            in.read();
            in.read();
            in.reset(); // throws
            assertEquals(4, in.tell());
            int c = in.read();
            System.out.println((char) c);
        }

        in.read(new byte[100]);
        assertEquals(hello.length, in.tell());

        in.close();
    }

}

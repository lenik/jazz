package net.bodz.bas.lop.util;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class TellableInputStreamTest {

    static byte[] hello = "hello, abcdefg".getBytes();

    @Test
    public void test1() throws IOException {
        TellableInputStream r = new TellableInputStream(new ByteArrayInputStream(hello));

        assertEquals(0, r.tell());
        r.read();
        r.read();
        assertEquals(2, r.tell());
        r.read(new byte[2]);
        assertEquals(4, r.tell());
        r.mark(3);
        r.read();
        r.read();
        r.reset();
        assertEquals(4, r.tell());
        r.read();
        r.reset();
        assertEquals(4, r.tell());

        if (true) {
            r.read();
            r.read();
            r.read();
            r.read();
            r.read();
            r.reset(); // throws
            assertEquals(4, r.tell());
            int c = r.read();
            System.out.println((char) c);
        }

        r.read(new byte[100]);
        assertEquals(hello.length, r.tell());
    }

}

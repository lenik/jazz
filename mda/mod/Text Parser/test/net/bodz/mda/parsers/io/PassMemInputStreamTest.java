package net.bodz.mda.parsers.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class PassMemInputStreamTest {

    static byte[] src = "0123456789abcdefg".getBytes();

    @Test
    public void test1() throws IOException {
        PassMemInputStream r = new PassMemInputStream(new ByteArrayInputStream(
                src));
        assertEquals("", r.getMemString());
        r.read();
        assertEquals("0", r.getMemString());
        r.read(new byte[3]);
        assertEquals("0123", r.getMemString());
        r.skip(1);
        assertEquals("", r.getMemString());
        r.read();
        assertEquals("5", r.getMemString());
        r.mark(3);
        r.read(new byte[5]);
        assertEquals("56789a", r.getMemString());
        r.reset();
        assertEquals("5", r.getMemString());
        r.read();
        assertEquals("56", r.getMemString());
        r.read(new byte[8]);
        assertEquals("56789abcde", r.getMemString());
        r.keepOnly(3);
        assertEquals("cde", r.getMemString());
        r.drop();
        assertEquals("", r.getMemString());
        r.read();
        assertEquals("f", r.getMemString());
        r.reset();
        assertEquals("", r.getMemString());
    }

    public static void main(String[] args) throws IOException {
        new PassMemInputStreamTest().test1();
    }

}

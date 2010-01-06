package net.bodz.bas.text.lop.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TellableReaderTest {

    static String hello = "hello, abcdefg";

    @Test
    public void test1() throws IOException {
        TellableReader r = new TellableReader(new StringReader(hello));

        assertEquals(0, r.tell());
        r.read();
        r.read();
        assertEquals(2, r.tell());
        r.read(new char[2]);
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

        r.read(new char[100]);
        assertEquals(hello.length(), r.tell());
    }

}

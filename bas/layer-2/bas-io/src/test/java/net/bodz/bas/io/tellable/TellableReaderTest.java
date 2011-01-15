package net.bodz.bas.io.tellable;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class TellableReaderTest
        extends Assert {

    static String hello = "hello, abcdefg";

    @Test
    public void test1()
            throws IOException {
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

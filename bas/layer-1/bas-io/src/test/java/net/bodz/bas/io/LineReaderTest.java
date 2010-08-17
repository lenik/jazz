package net.bodz.bas.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import junit.framework.TestCase;

import org.junit.Test;

public class LineReaderTest
        extends TestCase {

    @Test
    public void test1()
            throws IOException {
        Reader reader = new StringReader("hello, \nworld.\r\nThis is \rCaynoh.");
        // reader = new BufferedReader(reader);
        LineReader lr = new LineReader(reader);
        assertEquals((int) 'h', lr.read());
        assertEquals((int) 'e', lr.read());
        lr.mark(100);
        assertEquals((int) 'l', lr.read());
        assertEquals("lo, \n", lr.readLine());
        assertEquals("world.\r\n", lr.readLine());
        lr.reset();
        assertEquals("llo, \n", lr.readLine());
        lr.reset();
        assertEquals("llo, \n", lr.readLine());
        assertEquals("wor", lr.readLine(3));
        assertEquals("ld.", lr.readLine(3));
        assertEquals("\r\n", lr.readLine(3));
        lr.reset();
        assertEquals("llo, ", lr.readLine(5));
        assertEquals((int) '\n', lr.read());
        assertEquals("world.\r\n", lr.readLine());

        assertEquals("This is \r", lr.readLine()); // 'C'aynoh in buffer
        lr.mark(100);
        assertEquals((int) 'C', lr.read());
        assertEquals((int) 'a', lr.read());
        assertEquals("ynoh.", lr.readLine());
        assertEquals(-1, lr.read());
        lr.reset();
        assertEquals("Caynoh.", lr.readLine());
    }

}

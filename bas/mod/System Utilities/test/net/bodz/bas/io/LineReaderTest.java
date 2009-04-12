package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class LineReaderTest {

    @Test
    public void test1() throws IOException {
        Reader reader = new StringReader("hello, \nworld.\r\nThis is \rCaynoh."); //$NON-NLS-1$
        // reader = new BufferedReader(reader);
        LineReader lr = new LineReader(reader);
        assertEquals((int) 'h', lr.read());
        assertEquals((int) 'e', lr.read());
        lr.mark(100);
        assertEquals((int) 'l', lr.read());
        assertEquals("lo, \n", lr.readLine()); //$NON-NLS-1$
        assertEquals("world.\r\n", lr.readLine()); //$NON-NLS-1$
        lr.reset();
        assertEquals("llo, \n", lr.readLine()); //$NON-NLS-1$
        lr.reset();
        assertEquals("llo, \n", lr.readLine()); //$NON-NLS-1$
        assertEquals("wor", lr.readLine(3)); //$NON-NLS-1$
        assertEquals("ld.", lr.readLine(3)); //$NON-NLS-1$
        assertEquals("\r\n", lr.readLine(3)); //$NON-NLS-1$
        lr.reset();
        assertEquals("llo, ", lr.readLine(5)); //$NON-NLS-1$
        assertEquals((int) '\n', lr.read());
        assertEquals("world.\r\n", lr.readLine()); //$NON-NLS-1$

        assertEquals("This is \r", lr.readLine()); // 'C'aynoh in buffer //$NON-NLS-1$
        lr.mark(100);
        assertEquals((int) 'C', lr.read());
        assertEquals((int) 'a', lr.read());
        assertEquals("ynoh.", lr.readLine()); //$NON-NLS-1$
        assertEquals(-1, lr.read());
        lr.reset();
        assertEquals("Caynoh.", lr.readLine()); //$NON-NLS-1$
    }

}

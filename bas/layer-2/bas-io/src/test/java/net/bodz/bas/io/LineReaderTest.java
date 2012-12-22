package net.bodz.bas.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class LineReaderTest
        extends Assert {

    @Test
    public void test1()
            throws IOException {
        Reader reader = new StringReader("hello, \nworld.\r\nThis is \rCaynoh.");
        // reader = new BufferedReader(reader);
        LineReader _reader = new LineReader(reader);

        assertEquals('h', _reader.read());
        assertEquals('e', _reader.read());

        _reader.mark(100);
        assertEquals('l', _reader.read());
        assertEquals("lo, \n", _reader.readLine());
        assertEquals("world.\r\n", _reader.readLine());

        _reader.reset();
        assertEquals("llo, \n", _reader.readLine());

        _reader.reset();
        assertEquals("llo, \n", _reader.readLine());
        assertEquals("wor", _reader.readLine(3));
        assertEquals("ld.", _reader.readLine(3));
        assertEquals("\r\n", _reader.readLine(3));

        _reader.reset();
        assertEquals("llo, ", _reader.readLine(5));
        assertEquals('\n', _reader.read());
        assertEquals("world.\r\n", _reader.readLine());
        assertEquals("This is \r", _reader.readLine()); // 'C'aynoh in buffer

        _reader.mark(100);
        assertEquals('C', _reader.read());
        assertEquals('a', _reader.read());
        assertEquals("ynoh.", _reader.readLine());
        assertEquals(-1, _reader.read());

        _reader.reset();
        assertEquals("Caynoh.", _reader.readLine());

        _reader.close();
    }

}

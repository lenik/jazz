package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;

import org.junit.Test;

public class WriterOutputStreamTest {

    @Test
    public void testWrite() throws IOException {
        StringWriter sw = new StringWriter();
        WriterOutputStream out = new WriterOutputStream(sw, "utf-8"); //$NON-NLS-1$
        out.write('a');
        assertEquals("a", sw.toString()); //$NON-NLS-1$
        out.write("bc".getBytes("ascii")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("abc", sw.toString()); //$NON-NLS-1$

        byte[] hello = "你好".getBytes("utf-8"); //$NON-NLS-1$ //$NON-NLS-2$
        for (int i = 0; i < hello.length; i++)
            out.write(hello[i]);
        assertEquals("abc你好", sw.toString()); //$NON-NLS-1$

        sw = new StringWriter();
        out = new WriterOutputStream(sw, "ascii") { //$NON-NLS-1$
            @Override
            protected void handleMalformed(ByteBuffer buffer) throws IOException {
                writer.write('M');
            }

            @Override
            protected void handleUnmappable(ByteBuffer buffer) throws IOException {
                writer.write('U');
            }
        };
        out.write('s');
        assertEquals("s", sw.toString()); //$NON-NLS-1$
        for (int i = 0; i < 3; i++)
            out.write(hello[i]);
        assertEquals("sMMM", sw.toString()); //$NON-NLS-1$
    }

}

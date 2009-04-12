package net.bodz.bas.io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;

import org.junit.Test;

public class WriterOutputStreamTest {

    @Test
    public void testWrite() throws IOException {
        StringWriter buf = new StringWriter();
        WriterOutputStream out = new WriterOutputStream(buf, "utf-8"); //$NON-NLS-1$
        out.write('a');
        assertEquals("a", buf.toString()); //$NON-NLS-1$
        out.write("bc".getBytes("ascii")); //$NON-NLS-1$ //$NON-NLS-2$
        assertEquals("abc", buf.toString()); //$NON-NLS-1$

        byte[] hello = "你好".getBytes("utf-8"); //$NON-NLS-1$ //$NON-NLS-2$
        for (int i = 0; i < hello.length; i++)
            out.write(hello[i]);
        assertEquals("abc你好", buf.toString()); //$NON-NLS-1$

        buf = new StringWriter();
        out = new WriterOutputStream(buf, "ascii") { //$NON-NLS-1$
            @Override
            protected void handleMalformed(ByteBuffer buffer)
                    throws IOException {
                writer.write('M');
            }

            @Override
            protected void handleUnmappable(ByteBuffer buffer)
                    throws IOException {
                writer.write('U');
            }
        };
        out.write('s');
        assertEquals("s", buf.toString()); //$NON-NLS-1$
        for (int i = 0; i < 3; i++)
            out.write(hello[i]);
        assertEquals("sMMM", buf.toString()); //$NON-NLS-1$
    }

}

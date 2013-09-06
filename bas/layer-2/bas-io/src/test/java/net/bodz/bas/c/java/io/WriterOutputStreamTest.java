package net.bodz.bas.c.java.io;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.c.java.io.WriterOutputStream;

public class WriterOutputStreamTest
        extends Assert {

    @Test
    public void testWrite()
            throws IOException {
        StringWriter buf = new StringWriter();
        WriterOutputStream out = new WriterOutputStream(buf, "utf-8");
        out.write('a');
        assertEquals("a", buf.toString());
        out.write("bc".getBytes("ascii"));
        assertEquals("abc", buf.toString());

        byte[] hello = "你好".getBytes("utf-8");
        for (int i = 0; i < hello.length; i++)
            out.write(hello[i]);
        assertEquals("abc你好", buf.toString());

        buf = new StringWriter();
        out = new WriterOutputStream(buf, "ascii") {
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
        assertEquals("s", buf.toString());
        for (int i = 0; i < 3; i++)
            out.write(hello[i]);
        assertEquals("sMMM", buf.toString());

        out.close();
    }

}

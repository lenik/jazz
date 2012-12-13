package net.bodz.bas.t.buffer;

import java.nio.ByteBuffer;

import org.junit.Assert;
import org.junit.Test;

public class BuffersTest
        extends Assert {

    @Test
    public void testInsert()
            throws Exception {
        String s = "0123456789________________________";
        byte[] v1 = s.getBytes();
        ByteBuffer bb1 = ByteBuffer.wrap(v1);
        assertEquals(s, Buffers.getString(bb1));
        bb1.limit(10);
        assertEquals("0123456789", Buffers.getString(bb1));
        bb1 = Buffers._insert(bb1, 3, "xyz".getBytes());
        assertEquals("012xyz3456789", Buffers.getString(bb1));
        bb1.position(3);
        bb1 = Buffers._insert(bb1, 3, "hello".getBytes());
        assertEquals("helloxyz3456789", Buffers.getString(bb1));
        bb1 = Buffers._insert(bb1, 3, "world".getBytes(), true);
        assertEquals("helloxyz3456789", Buffers.getString(bb1));
    }

}

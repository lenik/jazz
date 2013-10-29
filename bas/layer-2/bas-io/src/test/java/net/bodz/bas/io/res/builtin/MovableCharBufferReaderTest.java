package net.bodz.bas.io.res.builtin;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.t.buffer.MovableCharBuffer;

public class MovableCharBufferReaderTest
        extends Assert {

    @Test
    public void test() {
        MovableCharBuffer buf = new MovableCharBuffer(100);
        MovableCharBufferReader in = new MovableCharBufferReader(buf);
        // TODO not implemented.
    }

}

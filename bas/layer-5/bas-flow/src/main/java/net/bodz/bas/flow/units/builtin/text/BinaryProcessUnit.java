package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.flow.units.SISOUnit;

public abstract class BinaryProcessUnit
        extends SISOUnit {

    public abstract void recv(byte[] bytes, int start, int end)
            throws IOException;

    public void recv(byte[] bytes)
            throws IOException {
        recv(bytes, 0, bytes.length);
    }

    public void recv(ByteBuffer bytes)
            throws IOException {
        recv(bytes.array(), bytes.position(), bytes.limit());
    }

}

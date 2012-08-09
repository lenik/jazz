package net.bodz.bas.flow.unit.builtins;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.flow.unit.GenericUnit_1z;

public abstract class ByteProcessSinkUnit
        extends GenericUnit_1z {

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

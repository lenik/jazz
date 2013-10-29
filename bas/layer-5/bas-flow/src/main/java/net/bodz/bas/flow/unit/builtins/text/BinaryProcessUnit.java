package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.flow.unit.GenericUnit_11;

public abstract class BinaryProcessUnit
        extends GenericUnit_11 {

    @Override
    public void recv(Object data)
            throws IOException {

        if (data instanceof byte[]) {
            recv((byte[]) data);
            return;
        }

        if (data instanceof ByteBuffer) {
            recv((ByteBuffer) data);
            return;
        }

        throw new NotImplementedException();
    }

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

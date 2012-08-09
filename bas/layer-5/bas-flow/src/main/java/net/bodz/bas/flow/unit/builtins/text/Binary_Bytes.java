package net.bodz.bas.flow.unit.builtins.text;

import java.io.IOException;
import java.util.Arrays;

import net.bodz.bas.meta.optim.Stateless;

@Stateless
public class Binary_Bytes
        extends BinaryProcessUnit {

    @Override
    public void reset()
            throws IOException {
    }

    @Override
    public void flush()
            throws IOException {
    }

    @Override
    public void recv(byte[] bytes, int start, int end)
            throws IOException {
        byte[] copy = Arrays.copyOfRange(bytes, start, end);
        recv(copy);
    }

    @Override
    public void recv(byte[] bytes)
            throws IOException {
        send(bytes);
    }

}

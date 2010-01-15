package net.bodz.bas.flow.units.builtin.text;

import static net.bodz.bas.text.encodings.Encodings.HEX;

import java.io.IOException;
import java.util.Arrays;

import net.bodz.bas.flow.Stateless;

@Stateless
public class Binary_Hex
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
        String hex = HEX.encode(bytes);
        send(hex);
    }

}

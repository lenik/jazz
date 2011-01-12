package net.bodz.bas.flow.units.builtin.text;

import java.io.IOException;
import java.util.Arrays;

import net.bodz.bas.flow.Stateless;
import net.bodz.bas.text.codec.builtin.HexCodec;

@Stateless
public class Binary_Hex
        extends BinaryProcessUnit {

    static final HexCodec hexCodec = HexCodec.getInstance();

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
        String hex = hexCodec.encode(bytes);
        send(hex);
    }

}

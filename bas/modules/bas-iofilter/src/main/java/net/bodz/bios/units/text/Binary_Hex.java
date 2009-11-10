package net.bodz.bios.units.text;

import static net.bodz.bas.text.encodings.Encodings.HEX;
import static net.bodz.bas.types.util.ArrayOps.Bytes;

import java.io.IOException;

import net.bodz.bios.Stateless;

@Stateless
public class Binary_Hex extends BinaryProcessUnit {

    @Override
    public void reset() throws IOException {
    }

    @Override
    public void flush() throws IOException {
    }

    @Override
    public void recv(byte[] bytes, int start, int end) throws IOException {
        byte[] copy = Bytes.copyOfRange(bytes, start, end);
        recv(copy);
    }

    @Override
    public void recv(byte[] bytes) throws IOException {
        String hex = HEX.encode(bytes);
        send(hex);
    }

}

package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

public class Password
        implements IRxParser {

    byte[] data = new byte[4];

    public Password(byte... data) {
        if (data == null)
            throw new NullPointerException("data");
        if (data.length != 4)
            throw new IllegalArgumentException("Password size isn't 4 bytes.");
        this.data = data;
    }

    public Password(int... data) {
        if (data == null)
            throw new NullPointerException("data");
        if (data.length != 4)
            throw new IllegalArgumentException("Password size isn't 4 bytes.");
        for (int i = 0; i < data.length; i++)
            this.data[i] = (byte) data[i];
    }

    @Override
    public Password parse(RxPacket in)
            throws IOException {
        in.read(data);
        return this;
    }

    @Override
    public String toString() {
        return memCodec.encode(data);
    }

}

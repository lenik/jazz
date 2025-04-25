package net.bodz.bas.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import net.bodz.bas.meta.decl.NotNull;

public interface ILookBytesAhead
        extends ILookAhead<Byte> {

    @Override
    default Byte look()
            throws IOException {
        int byt = lookByte();
        if (byt == -1)
            return null;
        else
            return (byte) byt;
    }

    /**
     * @return -1 if EOF
     */
    int lookByte()
            throws IOException;

    /**
     * @return 0 at EOF
     */
    default int look(byte[] buf)
            throws IOException {
        return look(buf, 0, buf.length);
    }

    /**
     * @return actual number of bytes copied to the buf
     */
    int look(byte[] buf, int off, int len)
            throws IOException;

    default String look(int len)
            throws IOException {
        return look(len, StandardCharsets.UTF_8);
    }

    /**
     * @return null at EOF.
     */
    default String look(int len, @NotNull Charset charset)
            throws IOException {
        if (len == 0)
            return "";
        byte[] buf = new byte[len];
        look(buf);
        return new String(buf, charset);
    }

    NoLookBytesAhead NULL = new NoLookBytesAhead();

}

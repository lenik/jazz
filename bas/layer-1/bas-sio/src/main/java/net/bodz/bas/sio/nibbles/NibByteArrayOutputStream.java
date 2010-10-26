package net.bodz.bas.sio.nibbles;

import java.io.ByteArrayOutputStream;

public class NibByteArrayOutputStream
        extends ByteArrayOutputStream {

    public NibByteArrayOutputStream() {
        super();
    }

    public NibByteArrayOutputStream(int size) {
        super(size);
    }

    /**
     * Get the nibble contents in hex format.
     * <p>
     * See {@link #toString(String)} If you want to get encode the byte contents to string.
     */
    @Override
    public synchronized String toString() {
        byte[] buf = toByteArray();
        String hex = NibbleUtil.toHex(buf, 0, buf.length * 2);
        return hex;
    }

}

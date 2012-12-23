package net.bodz.bas.c.java.util.zip;

import java.util.zip.Checksum;

public abstract class AbstractZipChecksum
        implements Checksum, Cloneable {

    @Override
    public void update(byte[] bytes, int off, int len) {
        while (len-- > 0) {
            int b = (bytes[off++]) & 0xff;
            update(b);
        }
    }

    public void update(byte[] bytes) {
        update(bytes, 0, bytes.length);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + getValue();
    }

}

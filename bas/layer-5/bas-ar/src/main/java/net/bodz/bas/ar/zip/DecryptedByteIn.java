package net.bodz.bas.ar.zip;

import java.io.IOException;

import net.bodz.bas.io.DecoratedByteIn;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.meta.decl.NotNull;

public class DecryptedByteIn
        extends DecoratedByteIn {

    private static final long serialVersionUID = 1L;

    private final ZipEncryptKey key;

    public DecryptedByteIn(IByteIn in, ZipEncryptKey key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read()
            throws IOException {
        int b = super.read();
        if (b == -1)
            return -1;
        b = key.decrypt((byte) b);
        return b;
    }

    @Override
    public int read(@NotNull byte[] buf, int off, int len)
            throws IOException {
        int cb = super.read(buf, off, len);
        for (int i = 0; i < cb; i++) {
            buf[off] = key.decrypt(buf[off]);
        }
        return cb;
    }

    @Override
    public long skip(long n)
            throws IOException {
        long skipped = 0;
        while (n-- >= 0) {
            int b = read();
            if (b == -1)
                break;
            skipped++;
        }
        return skipped;
    }

}

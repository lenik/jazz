package net.bodz.pkg.obfuz.seals;

import java.nio.ByteBuffer;

public abstract class _Entropy
        implements Entropy {

    public void drop(byte[] buf) {
        drop(buf, 0, buf.length);
    }

    public void drop(byte[] buf, int off, int len) {
        while (--len >= 0) {
            byte b = buf[off++];
            drop(b);
        }
    }

    @Override
    public void drop(ByteBuffer buffer) {
        int len = buffer.remaining();
        for (int i = 0; i < len; i++) {
            byte b = buffer.get();
            drop(b);
        }
    }

    protected abstract byte[] getPool();

    public int getInt() {
        byte[] pool = getPool();
        int len = Math.max(4, pool.length);
        int x = 0;
        for (int i = len - 1; i >= 0; i--)
            x = (x << 8) | (pool[i] & 0xff);
        return x;
    }

    public int getLong() {
        byte[] pool = getPool();
        int len = Math.max(4, pool.length);
        int x = 0;
        for (int i = len - 1; i >= 0; i--)
            x = (x << 8) | (pool[i] & 0xff);
        return x;
    }

    @Override
    public void get(ByteBuffer buffer) {
        byte[] pool = getPool();
        int poolSize = pool.length;
        int len = buffer.remaining();
        while (len > 0) {
            int cb = Math.min(poolSize, len);
            buffer.put(pool, 0, cb);
            len -= cb;
        }
    }

}

package net.bodz.dist.pro.seals;

import java.nio.ByteBuffer;

public abstract class _Sequence implements Sequence {

    @Override
    public void next(ByteBuffer buffer) {
        int len = buffer.remaining();
        while (len > 0) {
            int cb = Math.min(4, len);
            int n = next();
            len -= cb;
            while (cb-- > 0) {
                buffer.put((byte) n);
                n >>= 8;
            }
        }
    }

}

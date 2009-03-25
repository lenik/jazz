package net.bodz.product.seals;

public abstract class _Entropy implements Entropy {

    @Override
    public void drop(byte[] buf, int off, int len) {
        while (--len >= 0) {
            byte b = buf[off++];
            drop(b);
        }
    }

}

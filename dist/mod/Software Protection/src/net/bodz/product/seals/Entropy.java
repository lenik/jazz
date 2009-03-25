package net.bodz.product.seals;

public interface Entropy {

    void drop(byte b);

    void drop(byte[] buf, int off, int len);

    int get();

}

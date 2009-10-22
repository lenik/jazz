package net.bodz.dist.pro.seals;

import java.nio.ByteBuffer;

public interface Entropy {

    void drop(byte b);

    void drop(ByteBuffer buffer);

    /** won't change the entropy state */
    void get(ByteBuffer buffer);

}

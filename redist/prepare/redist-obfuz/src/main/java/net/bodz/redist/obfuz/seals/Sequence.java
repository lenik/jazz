package net.bodz.redist.obfuz.seals;

import java.nio.ByteBuffer;

public interface Sequence {

    void reset();

    int next();

    void next(ByteBuffer buffer);

}

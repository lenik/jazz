package net.bodz.pkg.obfuz.seals;

import java.nio.ByteBuffer;

public interface ISequence {

    void reset();

    int next();

    void next(ByteBuffer buffer);

}

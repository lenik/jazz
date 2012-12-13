package net.bodz.bas.c.primitive;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class ByteComparator
        extends AbstractNonNullComparator<Byte> {

    @Override
    public int compareNonNull(Byte a, Byte b) {
        return a - b;
    }

    public static final ByteComparator INSTANCE = new ByteComparator();

}

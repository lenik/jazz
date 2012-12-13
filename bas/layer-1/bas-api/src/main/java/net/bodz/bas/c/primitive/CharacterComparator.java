package net.bodz.bas.c.primitive;

import net.bodz.bas.t.order.AbstractNonNullComparator;

public class CharacterComparator
        extends AbstractNonNullComparator<Character> {

    @Override
    public int compareNonNull(Character a, Character b) {
        return (int) a - (int) b;
    }

    public static final CharacterComparator INSTANCE = new CharacterComparator();

}

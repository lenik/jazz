package net.bodz.bas.util.primitive;

import net.bodz.bas.util.order.AbstractNonNullComparator;

public class CharacterComparator
        extends AbstractNonNullComparator<Character> {

    @Override
    public int compareNonNull(Character a, Character b) {
        return (int) a - (int) b;
    }

    public static final CharacterComparator INSTANCE = new CharacterComparator();

}

package net.bodz.bas.collection.comparator;

public class CharacterComparator
        extends AbstractNonNullComparator<Character> {

    @Override
    public int compareNonNull(Character a, Character b) {
        return (int) a - (int) b;
    }

    private static final CharacterComparator instance = new CharacterComparator();

    public static CharacterComparator getInstance() {
        return instance;
    }

}

package net.bodz.bas.collection.comparators;

public class CharacterComparator
        implements NonNullComparator<Character> {

    private CharacterComparator() {
    }

    @Override
    public int compare(Character a, Character b) {
        return (int) a - (int) b;
    }

    private static final CharacterComparator instance = new CharacterComparator();

    public static CharacterComparator getInstance() {
        return instance;
    }

}

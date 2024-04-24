package net.bodz.bas.t.vector;

public class CharArrayAsVector
        implements
            CharVector {

    private final char[] chars;
    private final int start;
    private final int end;

    public CharArrayAsVector(char[] chars) {
        this(chars, 0, chars.length);
    }

    public CharArrayAsVector(char[] chars, int start, int end) {
        if (chars == null)
            throw new NullPointerException("chars");
        this.chars = chars;
        this.start = start;
        this.end = end;
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public Character get(int index) {
        return chars[index];
    }

    @Override
    public void set(int index, Character value) {
        chars[index] = value == null ? 0 : value.charValue();
    }

    @Override
    public char charAt(int index) {
        return chars[index];
    }

    @Override
    public CharIterator iterator() {
        return new CharArrayCharIterator(chars, start, end);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (int i = start; i < end; i++)
            hash = hash * 251 + chars[i];
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        CharArrayAsVector other = (CharArrayAsVector) obj;
        if (end - start != other.end - other.start)
            return false;
        int i1 = start;
        int i2 = other.start;
        for (; i1 < end; i1++) {
            if (chars[i1] != other.chars[i2])
                return false;
            i2++;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(chars, start, end - start);
    }

}

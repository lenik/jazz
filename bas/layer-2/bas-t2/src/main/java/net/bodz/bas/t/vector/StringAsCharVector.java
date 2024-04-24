package net.bodz.bas.t.vector;

public class StringAsCharVector
        implements
            CharVector {

    private CharSequence string;
    private final int start;
    private final int end;

    public StringAsCharVector(CharSequence string) {
        this(string, 0, string.length());
    }

    public StringAsCharVector(CharSequence string, int start, int end) {
        if (string == null)
            throw new NullPointerException("string");
        this.string = string;
        this.start = start;
        this.end = end;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public Character get(int index) {
        return string.charAt(index);
    }

    @Override
    public void set(int index, Character value) {
        String s = string.toString();
        String left = s.substring(0, index);
        String right = s.substring(index + 1);
        string = left + value + right;
    }

    @Override
    public char charAt(int index) {
        return string.charAt(index);
    }

    @Override
    public CharIterator iterator() {
        return new StringCharIterator(string, start, end);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        for (int i = start; i < end; i++)
            hash = hash * 251 + string.charAt(i);
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
        StringAsCharVector other = (StringAsCharVector) obj;
        if (end - start != other.end - other.start)
            return false;
        int i1 = start;
        int i2 = other.start;
        for (; i1 < end; i1++) {
            if (string.charAt(i1) != other.charAt(i2))
                return false;
            i2++;
        }
        return true;

    }

    @Override
    public String toString() {
        if (start == 0 && end == string.length())
            return string.toString();
        else
            return string.toString().substring(start, end);
    }

}

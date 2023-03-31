package net.bodz.bas.io;

public class EncodedChar {

    public int byteCount;
    public char character;
    public boolean malformed;

    EncodedChar(int byteCount, char ch) {
        this.byteCount = byteCount;
        this.character = ch;
    }

    EncodedChar(int byteCount) {
        this.byteCount = byteCount;
        this.malformed = true;
    }

    public static EncodedChar encoded(int byteCount, char ch) {
        return new EncodedChar(byteCount, ch);
    }

    public static EncodedChar decoded(int byteCount, char ch) {
        return new EncodedChar(byteCount, ch);
    }

    public static EncodedChar malformed(int byteCount) {
        return new EncodedChar(byteCount);
    }

    @Override
    public String toString() {
        if (malformed)
            return byteCount + "|<malformed>";
        else
            return byteCount + "|" + character;
    }

}

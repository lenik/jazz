package net.bodz.bas.io.data;

public class ConvertedLengths {

    public int charCount;
    public int byteCount;

    public ConvertedLengths() {
    }

    public ConvertedLengths(int charCount, int byteCount) {
        this.charCount = charCount;
        this.byteCount = byteCount;
    }

    public static ConvertedLengths encoded(int charCount, int byteCount) {
        return new ConvertedLengths(charCount, byteCount);
    }

    public static ConvertedLengths decoded(int byteCount, int charCount) {
        return new ConvertedLengths(charCount, byteCount);
    }

    @Override
    public String toString() {
        return String.format("%d %s, %d %s", //
                charCount, charCount > 1 ? "chars" : "char", //
                byteCount, byteCount > 1 ? "bytes" : "byte");
    }

}

package net.bodz.bas.io;

public class EncodedCharArray {

    public int byteCount;
    public char[] charArray;

    public EncodedCharArray(int byteCount, char[] charArray) {
        this.byteCount = byteCount;
        this.charArray = charArray;
    }

    public EncodedString toEncodedString() {
        return new EncodedString(byteCount, charArray);
    }

    @Override
    public String toString() {
        return byteCount + "|" + new String(charArray);
    }

}

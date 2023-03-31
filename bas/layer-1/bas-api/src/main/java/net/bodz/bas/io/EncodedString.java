package net.bodz.bas.io;

public class EncodedString {

    public int byteCount;
    public String string;

    EncodedString(int byteCount, String string) {
        this.byteCount = byteCount;
        this.string = string;
    }

    EncodedString(int byteCount, char[] charArray) {
        this.byteCount = byteCount;
        this.string = new String(charArray);
    }

    public static EncodedString encoded(int byteCount, String string) {
        return new EncodedString(byteCount, string);
    }

    public static EncodedString encoded(int byteCount, char[] charArray) {
        return new EncodedString(byteCount, charArray);
    }

    public static EncodedString decoded(int byteCount, String string) {
        return new EncodedString(byteCount, string);
    }

    public static EncodedString decoded(int byteCount, char[] charArray) {
        return new EncodedString(byteCount, charArray);
    }

    @Override
    public String toString() {
        return byteCount + "|" + string;
    }

}

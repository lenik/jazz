package net.bodz.bas.t.vector;

import java.util.List;

public class Vectors {

    public static CharVector ofString(CharSequence string) {
        return new StringAsCharVector(string);
    }

    public static CharVector ofString(CharSequence string, int start, int end) {
        return new StringAsCharVector(string, start, end);
    }

    @SafeVarargs
    public static <T> ArrayAsVector<T> ofArray(T... array) {
        return new ArrayAsVector<T>(array);
    }

    public static <T> ListVector<T> ofList(List<T> list) {
        return new ListVector<T>(list);
    }

    public static <T> ListVector<T> ofSize(int size) {
        return new ListVector<T>(size);
    }

    public static CharVector ofCharArray(char[] chars) {
        return new CharArrayAsVector(chars);
    }

    public static CharVector ofCharArray(char[] chars, int start, int end) {
        return new CharArrayAsVector(chars, start, end);
    }

    public static ByteVector ofByteArray(byte[] bytes) {
        return new ByteArrayAsVector(bytes);
    }

    public static ByteVector ofByteArray(byte[] bytes, int start, int end) {
        return new ByteArrayAsVector(bytes, start, end);
    }

}

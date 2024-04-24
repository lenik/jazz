package net.bodz.bas.t.vector;

public interface CharVector
        extends
            Vector<Character> {

    @Override
    default byte byteAt(int index) {
        return (byte) charAt(index);
    }

    @Override
    default short shortAt(int index) {
        return (short) charAt(index);
    }

    @Override
    default int intAt(int index) {
        return charAt(index);
    }

    @Override
    default long longAt(int index) {
        return charAt(index);
    }

    @Override
    default float floatAt(int index) {
        return charAt(index);
    }

    @Override
    default double doubleAt(int index) {
        return charAt(index);
    }

    @Override
    default boolean booleanAt(int index) {
        return charAt(index) != 0;
    }

}

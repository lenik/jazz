package net.bodz.bas.t.vector;

public interface IntVector
        extends
            Vector<Integer> {

    @Override
    default byte byteAt(int index) {
        return (byte) intAt(index);
    }

    @Override
    default short shortAt(int index) {
        return (short) intAt(index);
    }

    @Override
    default long longAt(int index) {
        return intAt(index);
    }

    @Override
    default float floatAt(int index) {
        return intAt(index);
    }

    @Override
    default double doubleAt(int index) {
        return intAt(index);
    }

    @Override
    default boolean booleanAt(int index) {
        return intAt(index) != 0;
    }

    @Override
    default char charAt(int index) {
        return (char) intAt(index);
    }

}

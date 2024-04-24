package net.bodz.bas.t.vector;

public interface ByteVector
        extends
            Vector<Byte> {

    @Override
    default short shortAt(int index) {
        return (short) (byteAt(index) & 0xFF);
    }

    @Override
    default int intAt(int index) {
        return byteAt(index) & 0xFF;
    }

    @Override
    default long longAt(int index) {
        return byteAt(index) & 0xFF;
    }

    @Override
    default float floatAt(int index) {
        return byteAt(index) & 0xFF;
    }

    @Override
    default double doubleAt(int index) {
        return byteAt(index) & 0xFF;
    }

    @Override
    default boolean booleanAt(int index) {
        return byteAt(index) != 0;
    }

    @Override
    default char charAt(int index) {
        return (char) (byteAt(index) & 0xFF);
    }

}

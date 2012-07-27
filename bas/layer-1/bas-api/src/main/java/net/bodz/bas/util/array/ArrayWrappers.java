package net.bodz.bas.util.array;

public abstract class ArrayWrappers {

    public static <T> ArrayWrapper<T> wrap(T[] array) {
        return new ArrayWrapper<T>(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static <T> ArrayWrapper<T> wrap(T[] array, int start, int end) {
        return new ArrayWrapper<T>(array, start, end);
    }

    public static ByteArrayWrapper wrap(byte[] array) {
        return new ByteArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static ByteArrayWrapper wrap(byte[] array, int start, int end) {
        return new ByteArrayWrapper(array, start, end);
    }

    public static IntArrayWrapper wrap(int[] array) {
        return new IntArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static IntArrayWrapper wrap(int[] array, int start, int end) {
        return new IntArrayWrapper(array, start, end);
    }

    public static LongArrayWrapper wrap(long[] array) {
        return new LongArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static LongArrayWrapper wrap(long[] array, int start, int end) {
        return new LongArrayWrapper(array, start, end);
    }

    public static FloatArrayWrapper wrap(float[] array) {
        return new FloatArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static FloatArrayWrapper wrap(float[] array, int start, int end) {
        return new FloatArrayWrapper(array, start, end);
    }

    public static DoubleArrayWrapper wrap(double[] array) {
        return new DoubleArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static DoubleArrayWrapper wrap(double[] array, int start, int end) {
        return new DoubleArrayWrapper(array, start, end);
    }

    public static BooleanArrayWrapper wrap(boolean[] array) {
        return new BooleanArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static BooleanArrayWrapper wrap(boolean[] array, int start, int end) {
        return new BooleanArrayWrapper(array, start, end);
    }

    public static CharArrayWrapper wrap(char[] array) {
        return new CharArrayWrapper(array);
    }

    /**
     * @param start
     *            inclusive
     * @param end
     *            exclusive
     */
    public static CharArrayWrapper wrap(char[] array, int start, int end) {
        return new CharArrayWrapper(array, start, end);
    }

}

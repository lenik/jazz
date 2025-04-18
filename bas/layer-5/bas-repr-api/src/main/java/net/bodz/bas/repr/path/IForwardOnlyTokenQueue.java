package net.bodz.bas.repr.path;

import java.lang.reflect.Array;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Records the state of token preprocessing.
 *
 * When the dispatch started, the token queue contains tokens to be dispatched, and after dispatch
 * is completed, all processed tokens are consumed, rest only the unprocessed tokens.
 */
public interface IForwardOnlyTokenQueue
        extends Cloneable {

    String ATTRIBUTE_KEY = IForwardOnlyTokenQueue.class.getName();

    IForwardOnlyTokenQueue clone();

    /**
     * Get the available count of remaining tokens.
     *
     * @return Count of the remaining tokens.
     */
    int available();

    /**
     * Join the remaining tokens to a path string.
     *
     * @return The joined remaining path, without leading slash('/').
     */
    String getRemainingPath();

    /**
     * Test if the queue is empty.
     *
     * @return <code>true</code> If the queue is empty.
     */
    boolean isDone();

    boolean isStopped();

    void stop();

    /**
     * Skip a number of tokens.
     *
     * @param n
     *            Number of tokens to skip.
     */
    void skip(int n);

    /**
     * Shift out a number of head tokens.
     *
     * @return A token array consists of n tokens.
     * @throws IndexOutOfBoundsException
     *             If <code>n</code> is out of range.
     */
    String[] shift(int n);

    /**
     * Same as shift(available).
     */
    default String[] shiftAll() {
        return shift(available());
    }

    /**
     * Shift out the head token.
     *
     * @return <code>null</code> If no more token.
     */
    String shift();

    /**
     * Peek at the head token.
     *
     * @return <code>null</code> If no more token.
     */
    default String peek() {
        return peekAt(0);
    }

    @NotNull
    default String[] peek(int n) {
        if (n > available())
            n = available();
        String[] v = new String[n];
        for (int i = 0; i < n; i++)
            v[i] = peekAt(i);
        return v;
    }

    @NotNull
    default String peek(@NotNull String separator) {
        return peek(available(), separator);
    }

    @NotNull
    default String peek(int n, @NotNull String separator) {
        StringBuilder buf = new StringBuilder(n * 16);
        peek(buf, n, separator);
        return buf.toString();
    }

    default void peek(@NotNull StringBuilder buf, int n, @NotNull String separator) {
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            if (i != 0)
                buf.append(separator);
            buf.append(peekAt(i));
        }
    }

    /**
     * Peek at the n-th token from the head.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    String peekAt(int offset);

    default String peekAt(int offset, String defaultValue) {
        String token = peekAt(offset);
        return token != null ? token : defaultValue;
    }

    // ----------------------------------------- GROUP: BYTE -----------------------------------------

    /**
     * Shift out the head token as a byte.
     * <p>
     * If the head token isn't byte, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a byte number.
     */
    default Byte shiftByte()
            throws ParseException {
        Byte n = peekByte();
        if (n != null)
            shift();
        return n;
    }

    default byte shiftByte(byte fallback) {
        if (available() == 0)
            return fallback;
        byte n = peekByte(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as byte.
     *
     * @return <code>null</code> If no more token.
     */
    default Byte peekByte()
            throws ParseException {
        return peekByteAt(0);
    }

    default byte peekByte(byte fallback) {
        return peekByteAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as byte.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Byte peekByteAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as byte.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    byte peekByteAt(int offset, byte fallback);

    @NotNull
    default byte[] peekBytes(int n)
            throws ParseException {
        if (n > available())
            n = available();
        byte[] array = new byte[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekByteAt(i);
        }
        return array;
    }

    @NotNull
    default byte[] peekBytes(int n, byte fallback)
            throws ParseException {
        byte[] array = new byte[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekByteAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: SHORT -----------------------------------------

    /**
     * Shift out the head token as a short.
     * <p>
     * If the head token isn't short, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a short number.
     */
    default Short shiftShort()
            throws ParseException {
        Short n = peekShort();
        if (n != null)
            shift();
        return n;
    }

    default short shiftShort(short fallback) {
        if (available() == 0)
            return fallback;
        short n = peekShort(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as short.
     *
     * @return <code>null</code> If no more token.
     */
    default Short peekShort()
            throws ParseException {
        return peekShortAt(0);
    }

    default short peekShort(short fallback) {
        return peekShortAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as short.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Short peekShortAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as short.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    short peekShortAt(int offset, short fallback);

    @NotNull
    default short[] peekShorts(int n)
            throws ParseException {
        if (n > available())
            n = available();
        short[] array = new short[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekShortAt(i);
        }
        return array;
    }

    @NotNull
    default short[] peekShorts(int n, short fallback)
            throws ParseException {
        short[] array = new short[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekShortAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: INT -----------------------------------------

    /**
     * Shift out the head token as an int.
     * <p>
     * If the head token isn't int, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't an int number.
     */
    default Integer shiftInt()
            throws ParseException {
        Integer n = peekInt();
        if (n != null)
            shift();
        return n;
    }

    default int shiftInt(int fallback) {
        if (available() == 0)
            return fallback;
        int n = peekInt(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as integer.
     *
     * @return <code>null</code> If no more token.
     */
    default Integer peekInt()
            throws ParseException {
        return peekIntAt(0);
    }

    default int peekInt(int fallback) {
        return peekIntAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    Integer peekIntAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    int peekIntAt(int offset, int fallback);

    @NotNull
    default int[] peekInts(int n)
            throws ParseException {
        if (n > available())
            n = available();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekIntAt(i);
        }
        return array;
    }

    @NotNull
    default int[] peekInts(int n, int fallback) {
        int[] array = new int[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekIntAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: LONG -----------------------------------------

    /**
     * Shift out the head token as a long.
     * <p>
     * If the head token isn't long, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a long number.
     */
    default Long shiftLong()
            throws ParseException {
        Long n = peekLong();
        if (n != null)
            shift();
        return n;
    }

    default long shiftLong(long fallback) {
        if (available() == 0)
            return fallback;
        long n = peekLong(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as long.
     *
     * @return <code>null</code> If no more token.
     */
    default Long peekLong()
            throws ParseException {
        return peekLongAt(0);
    }

    default long peekLong(long fallback) {
        return peekLongAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Long peekLongAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    long peekLongAt(int offset, long fallback);

    @NotNull
    default long[] peekLongs(int n)
            throws ParseException {
        if (n > available())
            n = available();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekLongAt(i);
        }
        return array;
    }

    @NotNull
    default long[] peekLongs(int n, long fallback)
            throws ParseException {
        long[] array = new long[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekLongAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: FLOAT -----------------------------------------

    /**
     * Shift out the head token as a float.
     * <p>
     * If the head token isn't float, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a float number.
     */
    default Float shiftFloat()
            throws ParseException {
        Float n = peekFloat();
        if (n != null)
            shift();
        return n;
    }

    default float shiftFloat(float fallback) {
        if (available() == 0)
            return fallback;
        float n = peekFloat(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as float.
     *
     * @return <code>null</code> If no more token.
     */
    default Float peekFloat()
            throws ParseException {
        return peekFloatAt(0);
    }

    default float peekFloat(float fallback) {
        return peekFloatAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as float.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Float peekFloatAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as float.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    float peekFloatAt(int offset, float fallback);

    @NotNull
    default float[] peekFloats(int n)
            throws ParseException {
        if (n > available())
            n = available();
        float[] array = new float[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekFloatAt(i);
        }
        return array;
    }

    @NotNull
    default float[] peekFloats(int n, float fallback)
            throws ParseException {
        float[] array = new float[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekFloatAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: DOUBLE -----------------------------------------

    /**
     * Shift out the head token as a double.
     * <p>
     * If the head token isn't double, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a double number.
     */
    default Double shiftDouble()
            throws ParseException {
        Double n = peekDouble();
        if (n != null)
            shift();
        return n;
    }

    default double shiftDouble(double fallback) {
        if (available() == 0)
            return fallback;
        double n = peekDouble(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as double.
     *
     * @return <code>null</code> If no more token.
     */
    default Double peekDouble()
            throws ParseException {
        return peekDoubleAt(0);
    }

    default double peekDouble(double fallback) {
        return peekDoubleAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as double.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Double peekDoubleAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as double.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    double peekDoubleAt(int offset, double fallback);

    @NotNull
    default double[] peekDoubles(int n)
            throws ParseException {
        if (n > available())
            n = available();
        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekDoubleAt(i);
        }
        return array;
    }

    @NotNull
    default double[] peekDoubles(int n, double fallback)
            throws ParseException {
        double[] array = new double[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekDoubleAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: BOOLEAN -----------------------------------------

    /**
     * Shift out the head token as a boolean.
     * <p>
     * If the head token isn't boolean, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a boolean number.
     */
    default Boolean shiftBoolean()
            throws ParseException {
        Boolean n = peekBoolean();
        if (n != null)
            shift();
        return n;
    }

    default boolean shiftBoolean(boolean fallback) {
        if (available() == 0)
            return fallback;
        boolean n = peekBoolean(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as boolean.
     *
     * @return <code>null</code> If no more token.
     */
    default Boolean peekBoolean()
            throws ParseException {
        return peekBooleanAt(0);
    }

    default boolean peekBoolean(boolean fallback) {
        return peekBooleanAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as boolean.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Boolean peekBooleanAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as boolean.
     *
     * @param offset
     *            The index offset, <code>0</code> if not specified.
     */
    boolean peekBooleanAt(int offset, boolean fallback);

    @NotNull
    default boolean[] peekBooleans(int n)
            throws ParseException {
        if (n > available())
            n = available();
        boolean[] array = new boolean[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekBooleanAt(i);
        }
        return array;
    }

    @NotNull
    default boolean[] peekBooleans(int n, boolean fallback)
            throws ParseException {
        boolean[] array = new boolean[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekBooleanAt(i, fallback);
        }
        return array;
    }

    // ----------------------------------------- GROUP: ENUM -----------------------------------------

    /**
     * Shift out the head token as a enum.
     * <p>
     * If the head token isn't enum, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a enum number.
     */
    default <E extends Enum<E>> E shift(Class<E> enumType)
            throws ParseException {
        E n = peek(enumType);
        if (n != null)
            shift();
        return n;
    }

    default <E extends Enum<E>> E shift(Class<E> enumType, E fallback) {
        if (available() == 0)
            return fallback;
        E n = peek(enumType, fallback);
        shift();
        return n;
    }

    default <E extends Enum<E>> E peek(Class<E> enumType)
            throws ParseException {
        return peekAt(enumType, 0);
    }

    default <E extends Enum<E>> E peek(Class<E> enumType, E fallback) {
        return peekAt(enumType, 0, fallback);
    }

    <E extends Enum<E>> E peekAt(Class<E> enumType, int offset)
            throws ParseException;

    <E extends Enum<E>> E peekAt(Class<E> enumType, int offset, E fallback);

    @NotNull
    default <E extends Enum<E>> E[] peek(Class<E> enumType, int n)
            throws ParseException {
        if (n > available())
            n = available();

        @SuppressWarnings("unchecked")
        E[] array = (E[]) Array.newInstance(enumType, n);

        for (int i = 0; i < n; i++) {
            array[i] = peekAt(enumType, i);
        }
        return array;
    }

    @NotNull
    default <E extends Enum<E>> E[] peek(Class<E> enumType, int n, E fallback) {
        if (n > available())
            n = available();

        @SuppressWarnings("unchecked")
        E[] array = (E[]) Array.newInstance(enumType, n);

        for (int i = 0; i < n; i++) {
            array[i] = peekAt(enumType, i, fallback);
        }
        return array;
    }

}

package net.bodz.bas.repr.path;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Records the state of token preprocessing.
 * <p>
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
     * @param n Number of tokens to skip.
     */
    void skip(int n);

    /**
     * Shift out a number of head tokens.
     *
     * @return A token array consists of n tokens.
     * @throws IndexOutOfBoundsException If <code>n</code> is out of range.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    String peekAt(int offset);

    default String peekAt(int offset, String defaultValue) {
        String token = peekAt(offset);
        return token != null ? token : defaultValue;
    }

    // ----------------------------------------- GROUP: CHAR -----------------------------------------

    /**
     * Shift out the head token as a char.
     * <p>
     * If the head token isn't char, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a char number.
     */
    default Character shiftChar()
            throws ParseException {
        Character n = peekChar();
        if (n != null)
            shift();
        return n;
    }

    default char shiftChar(char fallback) {
        if (available() == 0)
            return fallback;
        char n = peekChar(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as char.
     *
     * @return <code>null</code> If no more token.
     */
    default Character peekChar()
            throws ParseException {
        return peekCharAt(0);
    }

    default char peekChar(char fallback) {
        return peekCharAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as char.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Character peekCharAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as char.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    char peekCharAt(int offset, char fallback);

    @NotNull
    default char[] peekChars(int n)
            throws ParseException {
        if (n > available())
            n = available();
        char[] array = new char[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekCharAt(i);
        }
        return array;
    }

    @NotNull
    default char[] peekChars(int n, char fallback)
            throws ParseException {
        char[] array = new char[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekCharAt(i, fallback);
        }
        return array;
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Byte peekByteAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as byte.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Short peekShortAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as short.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist, or it's not a int integer.
     */
    Integer peekIntAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as int.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Long peekLongAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as long.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Float peekFloatAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as float.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Double peekDoubleAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as double.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Boolean peekBooleanAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as boolean.
     *
     * @param offset The index offset, <code>0</code> if not specified.
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


    // ----------------------------------------- GROUP: BigInteger -----------------------------------------

    /**
     * Shift out the head token as a BigInteger.
     * <p>
     * If the head token isn't BigInteger, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a BigInteger number.
     */
    default BigInteger shiftBigInteger()
            throws ParseException {
        BigInteger n = peekBigInteger();
        if (n != null)
            shift();
        return n;
    }

    default BigInteger shiftBigInteger(BigInteger fallback) {
        if (available() == 0)
            return fallback;
        BigInteger n = peekBigInteger(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as BigInteger.
     *
     * @return <code>null</code> If no more token.
     */
    default BigInteger peekBigInteger()
            throws ParseException {
        return peekBigIntegerAt(0);
    }

    default BigInteger peekBigInteger(BigInteger fallback) {
        return peekBigIntegerAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as BigInteger.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    BigInteger peekBigIntegerAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as BigInteger.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    BigInteger peekBigIntegerAt(int offset, BigInteger fallback);

    @NotNull
    default BigInteger[] peekBigIntegers(int n)
            throws ParseException {
        if (n > available())
            n = available();
        BigInteger[] array = new BigInteger[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekBigIntegerAt(i);
        }
        return array;
    }

    @NotNull
    default BigInteger[] peekBigIntegers(int n, BigInteger fallback)
            throws ParseException {
        BigInteger[] array = new BigInteger[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekBigIntegerAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: BIGDECIMAL -----------------------------------------

    /**
     * Shift out the head token as a BigDecimal.
     * <p>
     * If the head token isn't BigInteger, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a BigDecimal number.
     */
    default BigDecimal shiftBigDecimal()
            throws ParseException {
        BigDecimal n = peekBigDecimal();
        if (n != null)
            shift();
        return n;
    }

    default BigDecimal shiftBigDecimal(BigDecimal fallback) {
        if (available() == 0)
            return fallback;
        BigDecimal n = peekBigDecimal(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as BigDecimal.
     *
     * @return <code>null</code> If no more token.
     */
    default BigDecimal peekBigDecimal()
            throws ParseException {
        return peekBigDecimalAt(0);
    }

    default BigDecimal peekBigDecimal(BigDecimal fallback) {
        return peekBigDecimalAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as BigDecimal.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    BigDecimal peekBigDecimalAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as BigDecimal.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    BigDecimal peekBigDecimalAt(int offset, BigDecimal fallback);

    @NotNull
    default BigDecimal[] peekBigDecimals(int n)
            throws ParseException {
        if (n > available())
            n = available();
        BigDecimal[] array = new BigDecimal[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekBigDecimalAt(i);
        }
        return array;
    }

    @NotNull
    default BigDecimal[] peekBigDecimals(int n, BigDecimal fallback)
            throws ParseException {
        BigDecimal[] array = new BigDecimal[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekBigDecimalAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: ZonedDateTime -----------------------------------------

    /**
     * Shift out the head token as a ZonedDateTime.
     * <p>
     * If the head token isn't ZonedDateTime, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a ZonedDateTime number.
     */
    default ZonedDateTime shiftZonedDateTime()
            throws ParseException {
        ZonedDateTime n = peekZonedDateTime();
        if (n != null)
            shift();
        return n;
    }

    default ZonedDateTime shiftZonedDateTime(ZonedDateTime fallback) {
        if (available() == 0)
            return fallback;
        ZonedDateTime n = peekZonedDateTime(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as ZonedDateTime.
     *
     * @return <code>null</code> If no more token.
     */
    default ZonedDateTime peekZonedDateTime()
            throws ParseException {
        return peekZonedDateTimeAt(0);
    }

    default ZonedDateTime peekZonedDateTime(ZonedDateTime fallback) {
        return peekZonedDateTimeAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as ZonedDateTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    ZonedDateTime peekZonedDateTimeAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as ZonedDateTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    ZonedDateTime peekZonedDateTimeAt(int offset, ZonedDateTime fallback);

    @NotNull
    default ZonedDateTime[] peekZonedDateTimes(int n)
            throws ParseException {
        if (n > available())
            n = available();
        ZonedDateTime[] array = new ZonedDateTime[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekZonedDateTimeAt(i);
        }
        return array;
    }

    @NotNull
    default ZonedDateTime[] peekZonedDateTimes(int n, ZonedDateTime fallback)
            throws ParseException {
        ZonedDateTime[] array = new ZonedDateTime[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekZonedDateTimeAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: OffsetDateTime -----------------------------------------

    /**
     * Shift out the head token as a OffsetDateTime.
     * <p>
     * If the head token isn't OffsetDateTime, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a OffsetDateTime number.
     */
    default OffsetDateTime shiftOffsetDateTime()
            throws ParseException {
        OffsetDateTime n = peekOffsetDateTime();
        if (n != null)
            shift();
        return n;
    }

    default OffsetDateTime shiftOffsetDateTime(OffsetDateTime fallback) {
        if (available() == 0)
            return fallback;
        OffsetDateTime n = peekOffsetDateTime(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as OffsetDateTime.
     *
     * @return <code>null</code> If no more token.
     */
    default OffsetDateTime peekOffsetDateTime()
            throws ParseException {
        return peekOffsetDateTimeAt(0);
    }

    default OffsetDateTime peekOffsetDateTime(OffsetDateTime fallback) {
        return peekOffsetDateTimeAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as OffsetDateTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    OffsetDateTime peekOffsetDateTimeAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as OffsetDateTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    OffsetDateTime peekOffsetDateTimeAt(int offset, OffsetDateTime fallback);

    @NotNull
    default OffsetDateTime[] peekOffsetDateTimes(int n)
            throws ParseException {
        if (n > available())
            n = available();
        OffsetDateTime[] array = new OffsetDateTime[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekOffsetDateTimeAt(i);
        }
        return array;
    }

    @NotNull
    default OffsetDateTime[] peekOffsetDateTimes(int n, OffsetDateTime fallback)
            throws ParseException {
        OffsetDateTime[] array = new OffsetDateTime[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekOffsetDateTimeAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: OffsetTime -----------------------------------------

    /**
     * Shift out the head token as a OffsetTime.
     * <p>
     * If the head token isn't OffsetTime, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a OffsetTime number.
     */
    default OffsetTime shiftOffsetTime()
            throws ParseException {
        OffsetTime n = peekOffsetTime();
        if (n != null)
            shift();
        return n;
    }

    default OffsetTime shiftOffsetTime(OffsetTime fallback) {
        if (available() == 0)
            return fallback;
        OffsetTime n = peekOffsetTime(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as OffsetTime.
     *
     * @return <code>null</code> If no more token.
     */
    default OffsetTime peekOffsetTime()
            throws ParseException {
        return peekOffsetTimeAt(0);
    }

    default OffsetTime peekOffsetTime(OffsetTime fallback) {
        return peekOffsetTimeAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as OffsetTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    OffsetTime peekOffsetTimeAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as OffsetTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    OffsetTime peekOffsetTimeAt(int offset, OffsetTime fallback);

    @NotNull
    default OffsetTime[] peekOffsetTimes(int n)
            throws ParseException {
        if (n > available())
            n = available();
        OffsetTime[] array = new OffsetTime[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekOffsetTimeAt(i);
        }
        return array;
    }

    @NotNull
    default OffsetTime[] peekOffsetTimes(int n, OffsetTime fallback)
            throws ParseException {
        OffsetTime[] array = new OffsetTime[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekOffsetTimeAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: LocalDateTime -----------------------------------------

    /**
     * Shift out the head token as a LocalDateTime.
     * <p>
     * If the head token isn't LocalDateTime, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a LocalDateTime number.
     */
    default LocalDateTime shiftLocalDateTime()
            throws ParseException {
        LocalDateTime n = peekLocalDateTime();
        if (n != null)
            shift();
        return n;
    }

    default LocalDateTime shiftLocalDateTime(LocalDateTime fallback) {
        if (available() == 0)
            return fallback;
        LocalDateTime n = peekLocalDateTime(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as LocalDateTime.
     *
     * @return <code>null</code> If no more token.
     */
    default LocalDateTime peekLocalDateTime()
            throws ParseException {
        return peekLocalDateTimeAt(0);
    }

    default LocalDateTime peekLocalDateTime(LocalDateTime fallback) {
        return peekLocalDateTimeAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as LocalDateTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    LocalDateTime peekLocalDateTimeAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as LocalDateTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    LocalDateTime peekLocalDateTimeAt(int offset, LocalDateTime fallback);

    @NotNull
    default LocalDateTime[] peekLocalDateTimes(int n)
            throws ParseException {
        if (n > available())
            n = available();
        LocalDateTime[] array = new LocalDateTime[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekLocalDateTimeAt(i);
        }
        return array;
    }

    @NotNull
    default LocalDateTime[] peekLocalDateTimes(int n, LocalDateTime fallback)
            throws ParseException {
        LocalDateTime[] array = new LocalDateTime[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekLocalDateTimeAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: LocalDate -----------------------------------------

    /**
     * Shift out the head token as a LocalDate.
     * <p>
     * If the head token isn't LocalDate, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a LocalDate number.
     */
    default LocalDate shiftLocalDate()
            throws ParseException {
        LocalDate n = peekLocalDate();
        if (n != null)
            shift();
        return n;
    }

    default LocalDate shiftLocalDate(LocalDate fallback) {
        if (available() == 0)
            return fallback;
        LocalDate n = peekLocalDate(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as LocalDate.
     *
     * @return <code>null</code> If no more token.
     */
    default LocalDate peekLocalDate()
            throws ParseException {
        return peekLocalDateAt(0);
    }

    default LocalDate peekLocalDate(LocalDate fallback) {
        return peekLocalDateAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as LocalDate.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    LocalDate peekLocalDateAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as LocalDate.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    LocalDate peekLocalDateAt(int offset, LocalDate fallback);

    @NotNull
    default LocalDate[] peekLocalDates(int n)
            throws ParseException {
        if (n > available())
            n = available();
        LocalDate[] array = new LocalDate[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekLocalDateAt(i);
        }
        return array;
    }

    @NotNull
    default LocalDate[] peekLocalDates(int n, LocalDate fallback)
            throws ParseException {
        LocalDate[] array = new LocalDate[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekLocalDateAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: LocalTime -----------------------------------------

    /**
     * Shift out the head token as a LocalTime.
     * <p>
     * If the head token isn't LocalTime, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a LocalTime number.
     */
    default LocalTime shiftLocalTime()
            throws ParseException {
        LocalTime n = peekLocalTime();
        if (n != null)
            shift();
        return n;
    }

    default LocalTime shiftLocalTime(LocalTime fallback) {
        if (available() == 0)
            return fallback;
        LocalTime n = peekLocalTime(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as LocalTime.
     *
     * @return <code>null</code> If no more token.
     */
    default LocalTime peekLocalTime()
            throws ParseException {
        return peekLocalTimeAt(0);
    }

    default LocalTime peekLocalTime(LocalTime fallback) {
        return peekLocalTimeAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as LocalTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    LocalTime peekLocalTimeAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as LocalTime.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    LocalTime peekLocalTimeAt(int offset, LocalTime fallback);

    @NotNull
    default LocalTime[] peekLocalTimes(int n)
            throws ParseException {
        if (n > available())
            n = available();
        LocalTime[] array = new LocalTime[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekLocalTimeAt(i);
        }
        return array;
    }

    @NotNull
    default LocalTime[] peekLocalTimes(int n, LocalTime fallback)
            throws ParseException {
        LocalTime[] array = new LocalTime[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekLocalTimeAt(i, fallback);
        }
        return array;
    }


    // ----------------------------------------- GROUP: Instant -----------------------------------------

    /**
     * Shift out the head token as a Instant.
     * <p>
     * If the head token isn't Instant, shift doesn't happen and <code>null</code> is returned.
     *
     * @return <code>null</code> If no more token, or the head token isn't a Instant number.
     */
    default Instant shiftInstant()
            throws ParseException {
        Instant n = peekInstant();
        if (n != null)
            shift();
        return n;
    }

    default Instant shiftInstant(Instant fallback) {
        if (available() == 0)
            return fallback;
        Instant n = peekInstant(fallback);
        shift();
        return n;
    }

    /**
     * Peek at the head token as Instant.
     *
     * @return <code>null</code> If no more token.
     */
    default Instant peekInstant()
            throws ParseException {
        return peekInstantAt(0);
    }

    default Instant peekInstant(Instant fallback) {
        return peekInstantAt(0, fallback);
    }

    /**
     * Peek at the n-th token from the head as Instant.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     * @return <code>null</code> If the token doesn't exist.
     */
    Instant peekInstantAt(int offset)
            throws ParseException;

    /**
     * Peek at the n-th token from the head as Instant.
     *
     * @param offset The index offset, <code>0</code> if not specified.
     */
    Instant peekInstantAt(int offset, Instant fallback);

    @NotNull
    default Instant[] peekInstants(int n)
            throws ParseException {
        if (n > available())
            n = available();
        Instant[] array = new Instant[n];
        for (int i = 0; i < n; i++) {
            array[i] = peekInstantAt(i);
        }
        return array;
    }

    @NotNull
    default Instant[] peekInstants(int n, Instant fallback)
            throws ParseException {
        Instant[] array = new Instant[n];
        if (n > available())
            n = available();
        for (int i = 0; i < n; i++) {
            array[i] = peekInstantAt(i, fallback);
        }
        return array;
    }


}

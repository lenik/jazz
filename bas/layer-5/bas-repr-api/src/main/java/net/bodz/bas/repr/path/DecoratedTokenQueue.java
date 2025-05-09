package net.bodz.bas.repr.path;

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
import net.bodz.bas.t.model.AbstractDecorator;

public abstract class DecoratedTokenQueue
        extends AbstractDecorator<ITokenQueue>
        implements ITokenQueue {

    private static final long serialVersionUID = 1L;

    public DecoratedTokenQueue(ITokenQueue _orig) {
        super(_orig);
    }

    @Override
    public abstract ITokenQueue clone();

    @Override
    public String getMethod() {
        return getWrapped().getMethod();
    }

    @Override
    public String getScheme() {
        return getWrapped().getScheme();
    }

    @Override
    public String getHost() {
        return getWrapped().getHost();
    }

    @Override
    public int getPort() {
        return getWrapped().getPort();
    }

    @Override
    public String getUserInfo() {
        return getWrapped().getUserInfo();
    }

    @Override
    public String getQuery() {
        return getWrapped().getQuery();
    }

    @Override
    public String getFragment() {
        return getWrapped().getFragment();
    }

    @Override
    public int size() {
        return getWrapped().size();
    }

    @Override
    public boolean isEmpty() {
        return getWrapped().isEmpty();
    }

    @Override
    public String get(int index) {
        return getWrapped().get(index);
    }

    @Override
    public int available() {
        return getWrapped().available();
    }

    @Override
    public String getRemainingPath() {
        return getWrapped().getRemainingPath();
    }

    @Override
    public boolean isEntered() {
        return getWrapped().isEntered();
    }

    @Override
    public boolean isDone() {
        return getWrapped().isDone();
    }

    @Override
    public boolean isStopped() {
        return getWrapped().isStopped();
    }

    @Override
    public void stop() {
        getWrapped().stop();
    }

    @Override
    public void skip(int n) {
        getWrapped().skip(n);
    }

    @Override
    public String[] shift(int n) {
        return getWrapped().shift(n);
    }

    @Override
    public String[] shiftAll() {
        return getWrapped().shiftAll();
    }

    @Override
    public String shift() {
        return getWrapped().shift();
    }

    @Override
    public String peek() {
        return getWrapped().peek();
    }

    @Override
    public String peekAt(int offset) {
        return getWrapped().peekAt(offset);
    }

    // ----------------------------------------- GROUP: STRING -----------------------------------------

    @NotNull
    @Override
    public String getString(int index)
            throws ParseException {
        return getWrapped().getString(index);
    }

    @Override
    public String getString(int index, String fallback) {
        return getWrapped().getString(index, fallback);
    }

    @Override
    public String shiftString()
            throws ParseException {
        return getWrapped().shiftString();
    }

    @Override
    public String shiftString(String fallback) {
        return getWrapped().shiftString(fallback);
    }

    @Override
    public String peekString()
            throws ParseException {
        return getWrapped().peekString();
    }

    @Override
    public String peekString(String fallback) {
        return getWrapped().peekString(fallback);
    }

    @Override
    public String peekStringAt(int offset)
            throws ParseException {
        return getWrapped().peekStringAt(offset);
    }

    @Override
    public String peekStringAt(int offset, String fallback) {
        return getWrapped().peekStringAt(offset, fallback);
    }

    @NotNull
    @Override
    public String[] peekStrings(int n)
            throws ParseException {
        return getWrapped().peekStrings(n);
    }

    @NotNull
    @Override
    public String[] peekStrings(int n, String fallback)
            throws ParseException {
        return getWrapped().peekStrings(n, fallback);
    }

    // ----------------------------------------- GROUP: CHAR -----------------------------------------

    @Override
    public char getChar(int index)
            throws ParseException {
        return getWrapped().getChar(index);
    }

    @Override
    public char getChar(int index, char fallback) {
        return getWrapped().getChar(index, fallback);
    }

    @Override
    public Character shiftChar()
            throws ParseException {
        return getWrapped().shiftChar();
    }

    @Override
    public char shiftChar(char fallback) {
        return getWrapped().shiftChar(fallback);
    }

    @Override
    public Character peekChar()
            throws ParseException {
        return getWrapped().peekChar();
    }

    @Override
    public char peekChar(char fallback) {
        return getWrapped().peekChar(fallback);
    }

    @Override
    public Character peekCharAt(int offset)
            throws ParseException {
        return getWrapped().peekCharAt(offset);
    }

    @Override
    public char peekCharAt(int offset, char fallback) {
        return getWrapped().peekCharAt(offset, fallback);
    }

    @NotNull
    @Override
    public char[] peekChars(int n)
            throws ParseException {
        return getWrapped().peekChars(n);
    }

    @NotNull
    @Override
    public char[] peekChars(int n, char fallback)
            throws ParseException {
        return getWrapped().peekChars(n, fallback);
    }

    // ----------------------------------------- GROUP: BYTE -----------------------------------------

    @Override
    public byte getByte(int index)
            throws ParseException {
        return getWrapped().getByte(index);
    }

    @Override
    public byte getByte(int index, byte fallback) {
        return getWrapped().getByte(index, fallback);
    }

    @Override
    public Byte shiftByte()
            throws ParseException {
        return getWrapped().shiftByte();
    }

    @Override
    public byte shiftByte(byte fallback) {
        return getWrapped().shiftByte(fallback);
    }

    @Override
    public Byte peekByte()
            throws ParseException {
        return getWrapped().peekByte();
    }

    @Override
    public byte peekByte(byte fallback) {
        return getWrapped().peekByte(fallback);
    }

    @Override
    public Byte peekByteAt(int offset)
            throws ParseException {
        return getWrapped().peekByteAt(offset);
    }

    @Override
    public byte peekByteAt(int offset, byte fallback) {
        return getWrapped().peekByteAt(offset, fallback);
    }

    @NotNull
    @Override
    public byte[] peekBytes(int n)
            throws ParseException {
        return getWrapped().peekBytes(n);
    }

    @NotNull
    @Override
    public byte[] peekBytes(int n, byte fallback)
            throws ParseException {
        return getWrapped().peekBytes(n, fallback);
    }

    // ----------------------------------------- GROUP: SHORT -----------------------------------------

    @Override
    public short getShort(int index)
            throws ParseException {
        return getWrapped().getShort(index);
    }

    @Override
    public short getShort(int index, short fallback) {
        return getWrapped().getShort(index, fallback);
    }

    @Override
    public Short shiftShort()
            throws ParseException {
        return getWrapped().shiftShort();
    }

    @Override
    public short shiftShort(short fallback) {
        return getWrapped().shiftShort(fallback);
    }

    @Override
    public Short peekShort()
            throws ParseException {
        return getWrapped().peekShort();
    }

    @Override
    public short peekShort(short fallback) {
        return getWrapped().peekShort(fallback);
    }

    @Override
    public Short peekShortAt(int offset)
            throws ParseException {
        return getWrapped().peekShortAt(offset);
    }

    @Override
    public short peekShortAt(int offset, short fallback) {
        return getWrapped().peekShortAt(offset, fallback);
    }

    @NotNull
    @Override
    public short[] peekShorts(int n)
            throws ParseException {
        return getWrapped().peekShorts(n);
    }

    @NotNull
    @Override
    public short[] peekShorts(int n, short fallback)
            throws ParseException {
        return getWrapped().peekShorts(n, fallback);
    }

    // ----------------------------------------- GROUP: INT -----------------------------------------

    @Override
    public int getInt(int index)
            throws ParseException {
        return getWrapped().getInt(index);
    }

    @Override
    public int getInt(int index, int fallback) {
        return getWrapped().getInt(index, fallback);
    }

    @Override
    public Integer shiftInt()
            throws ParseException {
        return getWrapped().shiftInt();
    }

    @Override
    public int shiftInt(int fallback) {
        return getWrapped().shiftInt(fallback);
    }

    @Override
    public Integer peekInt()
            throws ParseException {
        return getWrapped().peekInt();
    }

    @Override
    public int peekInt(int fallback) {
        return getWrapped().peekInt(fallback);
    }

    @Override
    public Integer peekIntAt(int offset)
            throws ParseException {
        return getWrapped().peekIntAt(offset);
    }

    @Override
    public int peekIntAt(int offset, int fallback) {
        return getWrapped().peekIntAt(offset, fallback);
    }

    @NotNull
    @Override
    public int[] peekInts(int n)
            throws ParseException {
        return getWrapped().peekInts(n);
    }

    @NotNull
    @Override
    public int[] peekInts(int n, int fallback) {
        return getWrapped().peekInts(n, fallback);
    }

    // ----------------------------------------- GROUP: LONG -----------------------------------------

    @Override
    public long getLong(int index)
            throws ParseException {
        return getWrapped().getLong(index);
    }

    @Override
    public long getLong(int index, long fallback) {
        return getWrapped().getLong(index, fallback);
    }

    @Override
    public Long shiftLong()
            throws ParseException {
        return getWrapped().shiftLong();
    }

    @Override
    public long shiftLong(long fallback) {
        return getWrapped().shiftLong(fallback);
    }

    @Override
    public Long peekLong()
            throws ParseException {
        return getWrapped().peekLong();
    }

    @Override
    public long peekLong(long fallback) {
        return getWrapped().peekLong(fallback);
    }

    @Override
    public Long peekLongAt(int offset)
            throws ParseException {
        return getWrapped().peekLongAt(offset);
    }

    @Override
    public long peekLongAt(int offset, long fallback) {
        return getWrapped().peekLongAt(offset, fallback);
    }

    @NotNull
    @Override
    public long[] peekLongs(int n)
            throws ParseException {
        return getWrapped().peekLongs(n);
    }

    @NotNull
    @Override
    public long[] peekLongs(int n, long fallback)
            throws ParseException {
        return getWrapped().peekLongs(n, fallback);
    }

    // ----------------------------------------- GROUP: FLOAT -----------------------------------------

    @Override
    public float getFloat(int index)
            throws ParseException {
        return getWrapped().getFloat(index);
    }

    @Override
    public float getFloat(int index, float fallback) {
        return getWrapped().getFloat(index, fallback);
    }

    @Override
    public Float shiftFloat()
            throws ParseException {
        return getWrapped().shiftFloat();
    }

    @Override
    public float shiftFloat(float fallback) {
        return getWrapped().shiftFloat(fallback);
    }

    @Override
    public Float peekFloat()
            throws ParseException {
        return getWrapped().peekFloat();
    }

    @Override
    public float peekFloat(float fallback) {
        return getWrapped().peekFloat(fallback);
    }

    @Override
    public Float peekFloatAt(int offset)
            throws ParseException {
        return getWrapped().peekFloatAt(offset);
    }

    @Override
    public float peekFloatAt(int offset, float fallback) {
        return getWrapped().peekFloatAt(offset, fallback);
    }

    @NotNull
    @Override
    public float[] peekFloats(int n)
            throws ParseException {
        return getWrapped().peekFloats(n);
    }

    @NotNull
    @Override
    public float[] peekFloats(int n, float fallback)
            throws ParseException {
        return getWrapped().peekFloats(n, fallback);
    }

    // ----------------------------------------- GROUP: DOUBLE -----------------------------------------

    @Override
    public double getDouble(int index)
            throws ParseException {
        return getWrapped().getDouble(index);
    }

    @Override
    public double getDouble(int index, double fallback) {
        return getWrapped().getDouble(index, fallback);
    }

    @Override
    public Double shiftDouble()
            throws ParseException {
        return getWrapped().shiftDouble();
    }

    @Override
    public double shiftDouble(double fallback) {
        return getWrapped().shiftDouble(fallback);
    }

    @Override
    public Double peekDouble()
            throws ParseException {
        return getWrapped().peekDouble();
    }

    @Override
    public double peekDouble(double fallback) {
        return getWrapped().peekDouble(fallback);
    }

    @Override
    public Double peekDoubleAt(int offset)
            throws ParseException {
        return getWrapped().peekDoubleAt(offset);
    }

    @Override
    public double peekDoubleAt(int offset, double fallback) {
        return getWrapped().peekDoubleAt(offset, fallback);
    }

    @NotNull
    @Override
    public double[] peekDoubles(int n)
            throws ParseException {
        return getWrapped().peekDoubles(n);
    }

    @NotNull
    @Override
    public double[] peekDoubles(int n, double fallback)
            throws ParseException {
        return getWrapped().peekDoubles(n, fallback);
    }

    // ----------------------------------------- GROUP: BOOLEAN -----------------------------------------

    @Override
    public boolean getBoolean(int index)
            throws ParseException {
        return getWrapped().getBoolean(index);
    }

    @Override
    public boolean getBoolean(int index, boolean fallback) {
        return getWrapped().getBoolean(index, fallback);
    }

    @Override
    public Boolean shiftBoolean()
            throws ParseException {
        return getWrapped().shiftBoolean();
    }

    @Override
    public boolean shiftBoolean(boolean fallback) {
        return getWrapped().shiftBoolean(fallback);
    }

    @Override
    public Boolean peekBoolean()
            throws ParseException {
        return getWrapped().peekBoolean();
    }

    @Override
    public boolean peekBoolean(boolean fallback) {
        return getWrapped().peekBoolean(fallback);
    }

    @Override
    public Boolean peekBooleanAt(int offset)
            throws ParseException {
        return getWrapped().peekBooleanAt(offset);
    }

    @Override
    public boolean peekBooleanAt(int offset, boolean fallback) {
        return getWrapped().peekBooleanAt(offset, fallback);
    }

    @NotNull
    @Override
    public boolean[] peekBooleans(int n)
            throws ParseException {
        return getWrapped().peekBooleans(n);
    }

    @NotNull
    @Override
    public boolean[] peekBooleans(int n, boolean fallback)
            throws ParseException {
        return getWrapped().peekBooleans(n, fallback);
    }

    // ----------------------------------------- GROUP: ENUM -----------------------------------------

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException {
        return getWrapped().get(enumType, index);
    }

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback) {
        return getWrapped().get(enumType, index, fallback);
    }

    @Override
    public <E extends Enum<E>> E peek(Class<E> enumType)
            throws ParseException {
        return getWrapped().peek(enumType);
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset)
            throws ParseException {
        return getWrapped().peekAt(enumType, offset);
    }

    @NotNull
    @Override
    public <E extends Enum<E>> E[] peek(Class<E> enumType, int n)
            throws ParseException {
        return getWrapped().peek(enumType, n);
    }

    @Override
    public <E extends Enum<E>> E peek(Class<E> enumType, E fallback) {
        return getWrapped().peek(enumType, fallback);
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset, E fallback) {
        return getWrapped().peekAt(enumType, offset, fallback);
    }

    @NotNull
    @Override
    public <E extends Enum<E>> E[] peek(Class<E> enumType, int n, E fallback) {
        return getWrapped().peek(enumType, n, fallback);
    }

    // ----------------------------------------- GROUP: BigInteger -----------------------------------------

    @Override
    public BigInteger getBigInteger(int index)
            throws ParseException {
        return getWrapped().getBigInteger(index);
    }

    @Override
    public BigInteger getBigInteger(int index, BigInteger fallback) {
        return getWrapped().getBigInteger(index, fallback);
    }

    @Override
    public BigInteger shiftBigInteger()
            throws ParseException {
        return getWrapped().shiftBigInteger();
    }

    @Override
    public BigInteger shiftBigInteger(BigInteger fallback) {
        return getWrapped().shiftBigInteger(fallback);
    }

    @Override
    public BigInteger peekBigInteger()
            throws ParseException {
        return getWrapped().peekBigInteger();
    }

    @Override
    public BigInteger peekBigInteger(BigInteger fallback) {
        return getWrapped().peekBigInteger(fallback);
    }

    @Override
    public BigInteger peekBigIntegerAt(int offset)
            throws ParseException {
        return getWrapped().peekBigIntegerAt(offset);
    }

    @Override
    public BigInteger peekBigIntegerAt(int offset, BigInteger fallback) {
        return getWrapped().peekBigIntegerAt(offset, fallback);
    }

    @NotNull
    @Override
    public BigInteger[] peekBigIntegers(int n)
            throws ParseException {
        return getWrapped().peekBigIntegers(n);
    }

    @NotNull
    @Override
    public BigInteger[] peekBigIntegers(int n, BigInteger fallback)
            throws ParseException {
        return getWrapped().peekBigIntegers(n, fallback);
    }

    // ----------------------------------------- GROUP: BigDecimal -----------------------------------------

    @Override
    public BigDecimal getBigDecimal(int index)
            throws ParseException {
        return getWrapped().getBigDecimal(index);
    }

    @Override
    public BigDecimal getBigDecimal(int index, BigDecimal fallback) {
        return getWrapped().getBigDecimal(index, fallback);
    }

    @Override
    public BigDecimal shiftBigDecimal()
            throws ParseException {
        return getWrapped().shiftBigDecimal();
    }

    @Override
    public BigDecimal shiftBigDecimal(BigDecimal fallback) {
        return getWrapped().shiftBigDecimal(fallback);
    }

    @Override
    public BigDecimal peekBigDecimal()
            throws ParseException {
        return getWrapped().peekBigDecimal();
    }

    @Override
    public BigDecimal peekBigDecimal(BigDecimal fallback) {
        return getWrapped().peekBigDecimal(fallback);
    }

    @Override
    public BigDecimal peekBigDecimalAt(int offset)
            throws ParseException {
        return getWrapped().peekBigDecimalAt(offset);
    }

    @Override
    public BigDecimal peekBigDecimalAt(int offset, BigDecimal fallback) {
        return getWrapped().peekBigDecimalAt(offset, fallback);
    }

    @NotNull
    @Override
    public BigDecimal[] peekBigDecimals(int n)
            throws ParseException {
        return getWrapped().peekBigDecimals(n);
    }

    @NotNull
    @Override
    public BigDecimal[] peekBigDecimals(int n, BigDecimal fallback)
            throws ParseException {
        return getWrapped().peekBigDecimals(n, fallback);
    }

    // ----------------------------------------- GROUP: ZonedDateTime -----------------------------------------

    @Override
    public ZonedDateTime getZonedDateTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getZonedDateTime(index);
    }

    @Override
    public ZonedDateTime getZonedDateTime(int index, IDateTimeParseOptions options, ZonedDateTime fallback) {
        return getWrapped().getZonedDateTime(index, fallback);
    }


    @Override
    public ZonedDateTime peekZonedDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekZonedDateTimeAt(offset);
    }

    @Override
    public ZonedDateTime peekZonedDateTimeAt(int offset, IDateTimeParseOptions options, ZonedDateTime fallback) {
        return getWrapped().peekZonedDateTimeAt(offset, fallback);
    }

    // ----------------------------------------- GROUP: OffsetDateTime -----------------------------------------

    @Override
    public OffsetDateTime getOffsetDateTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getOffsetDateTime(index);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(int index, IDateTimeParseOptions options, OffsetDateTime fallback) {
        return getWrapped().getOffsetDateTime(index, fallback);
    }


    @Override
    public OffsetDateTime peekOffsetDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekOffsetDateTimeAt(offset);
    }

    @Override
    public OffsetDateTime peekOffsetDateTimeAt(int offset, IDateTimeParseOptions options, OffsetDateTime fallback) {
        return getWrapped().peekOffsetDateTimeAt(offset, fallback);
    }

    // ----------------------------------------- GROUP: OffsetTime -----------------------------------------

    @Override
    public OffsetTime getOffsetTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getOffsetTime(index);
    }

    @Override
    public OffsetTime getOffsetTime(int index, IDateTimeParseOptions options, OffsetTime fallback) {
        return getWrapped().getOffsetTime(index, fallback);
    }


    @Override
    public OffsetTime peekOffsetTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekOffsetTimeAt(offset);
    }

    @Override
    public OffsetTime peekOffsetTimeAt(int offset, IDateTimeParseOptions options, OffsetTime fallback) {
        return getWrapped().peekOffsetTimeAt(offset, fallback);
    }

    // ----------------------------------------- GROUP: LocalDateTime -----------------------------------------

    @Override
    public LocalDateTime getLocalDateTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getLocalDateTime(index);
    }

    @Override
    public LocalDateTime getLocalDateTime(int index, IDateTimeParseOptions options, LocalDateTime fallback) {
        return getWrapped().getLocalDateTime(index, fallback);
    }


    @Override
    public LocalDateTime peekLocalDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekLocalDateTimeAt(offset);
    }

    @Override
    public LocalDateTime peekLocalDateTimeAt(int offset, IDateTimeParseOptions options, LocalDateTime fallback) {
        return getWrapped().peekLocalDateTimeAt(offset, fallback);
    }

    // ----------------------------------------- GROUP: LocalDate -----------------------------------------

    @Override
    public LocalDate getLocalDate(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getLocalDate(index);
    }

    @Override
    public LocalDate getLocalDate(int index, IDateTimeParseOptions options, LocalDate fallback) {
        return getWrapped().getLocalDate(index, fallback);
    }


    @Override
    public LocalDate peekLocalDateAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekLocalDateAt(offset);
    }

    @Override
    public LocalDate peekLocalDateAt(int offset, IDateTimeParseOptions options, LocalDate fallback) {
        return getWrapped().peekLocalDateAt(offset, fallback);
    }

    // ----------------------------------------- GROUP: LocalTime -----------------------------------------

    @Override
    public LocalTime getLocalTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getLocalTime(index);
    }

    @Override
    public LocalTime getLocalTime(int index, IDateTimeParseOptions options, LocalTime fallback) {
        return getWrapped().getLocalTime(index, fallback);
    }


    @Override
    public LocalTime peekLocalTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekLocalTimeAt(offset);
    }

    @Override
    public LocalTime peekLocalTimeAt(int offset, IDateTimeParseOptions options, LocalTime fallback) {
        return getWrapped().peekLocalTimeAt(offset, fallback);
    }

    // ----------------------------------------- GROUP: Instant -----------------------------------------

    @Override
    public Instant getInstant(int index, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().getInstant(index);
    }

    @Override
    public Instant getInstant(int index, IDateTimeParseOptions options, Instant fallback) {
        return getWrapped().getInstant(index, fallback);
    }


    @Override
    public Instant peekInstantAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekInstantAt(offset);
    }

    @Override
    public Instant peekInstantAt(int offset, IDateTimeParseOptions options, Instant fallback) {
        return getWrapped().peekInstantAt(offset, fallback);
    }

}

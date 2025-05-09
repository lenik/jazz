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

public abstract class DecoratedBasicTokenQueue
        extends AbstractDecorator<IBasicTokenQueue>
        implements IBasicTokenQueue {

    private static final long serialVersionUID = 1L;

    public DecoratedBasicTokenQueue(IBasicTokenQueue _orig) {
        super(_orig);
    }

    @Override
    public IBasicTokenQueue clone() {
        return getWrapped().clone();
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
    public int size() {
        return getWrapped().size();
    }

    @Override
    public String get(int index) {
        return getWrapped().get(index);
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

    // ----------------------------------------- GROUP: String -----------------------------------------

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
    public String peekStringAt(int offset)
            throws ParseException {
        return getWrapped().peekStringAt(offset);
    }

    @Override
    public String peekStringAt(int offset, String fallback) {
        return getWrapped().peekStringAt(offset, fallback);
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
    public Character peekCharAt(int offset)
            throws ParseException {
        return getWrapped().peekCharAt(offset);
    }

    @Override
    public char peekCharAt(int offset, char fallback) {
        return getWrapped().peekCharAt(offset, fallback);
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
    public Byte peekByteAt(int offset)
            throws ParseException {
        return getWrapped().peekByteAt(offset);
    }

    @Override
    public byte peekByteAt(int offset, byte fallback) {
        return getWrapped().peekByteAt(offset, fallback);
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
    public Short peekShortAt(int offset)
            throws ParseException {
        return getWrapped().peekShortAt(offset);
    }

    @Override
    public short peekShortAt(int offset, short fallback) {
        return getWrapped().peekShortAt(offset, fallback);
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
    public Integer peekIntAt(int offset)
            throws ParseException {
        return getWrapped().peekIntAt(offset);
    }

    @Override
    public int peekIntAt(int offset, int fallback) {
        return getWrapped().peekIntAt(offset, fallback);
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
    public Long peekLongAt(int offset)
            throws ParseException {
        return getWrapped().peekLongAt(offset);
    }

    @Override
    public long peekLongAt(int offset, long fallback) {
        return getWrapped().peekLongAt(offset, fallback);
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
    public Float peekFloatAt(int offset)
            throws ParseException {
        return getWrapped().peekFloatAt(offset);
    }

    @Override
    public float peekFloatAt(int offset, float fallback) {
        return getWrapped().peekFloatAt(offset, fallback);
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
    public Double peekDoubleAt(int offset)
            throws ParseException {
        return getWrapped().peekDoubleAt(offset);
    }

    @Override
    public double peekDoubleAt(int offset, double fallback) {
        return getWrapped().peekDoubleAt(offset, fallback);
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
    public Boolean peekBooleanAt(int offset)
            throws ParseException {
        return getWrapped().peekBooleanAt(offset);
    }

    @Override
    public boolean peekBooleanAt(int offset, boolean fallback) {
        return getWrapped().peekBooleanAt(offset, fallback);
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
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset)
            throws ParseException {
        return getWrapped().peekAt(enumType, offset);
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset, E fallback) {
        return getWrapped().peekAt(enumType, offset, fallback);
    }

    // ----------------------------------------- GROUP: BIG INTEGER -----------------------------------------

    @Override
    public BigInteger getBigInteger(int index, BigInteger fallback) {
        return getWrapped().getBigInteger(index, fallback);
    }

    @Override
    public BigInteger getBigInteger(int index)
            throws ParseException {
        return getWrapped().getBigInteger(index);
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

    // ----------------------------------------- GROUP: BIG DECIMAL -----------------------------------------

    @Override
    public BigDecimal getBigDecimal(int index, BigDecimal fallback) {
        return getWrapped().getBigDecimal(index, fallback);
    }

    @Override
    public BigDecimal getBigDecimal(int index)
            throws ParseException {
        return getWrapped().getBigDecimal(index);
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

    // ----------------------------------------- GROUP: ZonedDateTime -----------------------------------------

    @Override
    public ZonedDateTime getZonedDateTime(int index, ZonedDateTime fallback) {
        return getWrapped().getZonedDateTime(index, fallback);
    }

    @Override
    public ZonedDateTime getZonedDateTime(int index)
            throws ParseException {
        return getWrapped().getZonedDateTime(index);
    }

    @Override
    public ZonedDateTime peekZonedDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekZonedDateTimeAt(offset, options);
    }

    @Override
    public ZonedDateTime peekZonedDateTimeAt(int offset, IDateTimeParseOptions options, ZonedDateTime fallback) {
        return getWrapped().peekZonedDateTimeAt(offset, options, fallback);
    }


    // ----------------------------------------- GROUP: OffsetDateTime -----------------------------------------

    @Override
    public OffsetDateTime getOffsetDateTime(int index, OffsetDateTime fallback) {
        return getWrapped().getOffsetDateTime(index, fallback);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(int index)
            throws ParseException {
        return getWrapped().getOffsetDateTime(index);
    }

    @Override
    public OffsetDateTime peekOffsetDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekOffsetDateTimeAt(offset, options);
    }

    @Override
    public OffsetDateTime peekOffsetDateTimeAt(int offset, IDateTimeParseOptions options, OffsetDateTime fallback) {
        return getWrapped().peekOffsetDateTimeAt(offset, options, fallback);
    }


    // ----------------------------------------- GROUP: OffsetTime -----------------------------------------

    @Override
    public OffsetTime getOffsetTime(int index, OffsetTime fallback) {
        return getWrapped().getOffsetTime(index, fallback);
    }

    @Override
    public OffsetTime getOffsetTime(int index)
            throws ParseException {
        return getWrapped().getOffsetTime(index);
    }

    @Override
    public OffsetTime peekOffsetTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekOffsetTimeAt(offset, options);
    }

    @Override
    public OffsetTime peekOffsetTimeAt(int offset, IDateTimeParseOptions options, OffsetTime fallback) {
        return getWrapped().peekOffsetTimeAt(offset, options, fallback);
    }


    // ----------------------------------------- GROUP: LocalDateTime -----------------------------------------

    @Override
    public LocalDateTime getLocalDateTime(int index, LocalDateTime fallback) {
        return getWrapped().getLocalDateTime(index, fallback);
    }

    @Override
    public LocalDateTime getLocalDateTime(int index)
            throws ParseException {
        return getWrapped().getLocalDateTime(index);
    }

    @Override
    public LocalDateTime peekLocalDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekLocalDateTimeAt(offset, options);
    }

    @Override
    public LocalDateTime peekLocalDateTimeAt(int offset, IDateTimeParseOptions options, LocalDateTime fallback) {
        return getWrapped().peekLocalDateTimeAt(offset, options, fallback);
    }


    // ----------------------------------------- GROUP: LocalDate -----------------------------------------

    @Override
    public LocalDate getLocalDate(int index, LocalDate fallback) {
        return getWrapped().getLocalDate(index, fallback);
    }

    @Override
    public LocalDate getLocalDate(int index)
            throws ParseException {
        return getWrapped().getLocalDate(index);
    }

    @Override
    public LocalDate peekLocalDateAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekLocalDateAt(offset, options);
    }

    @Override
    public LocalDate peekLocalDateAt(int offset, IDateTimeParseOptions options, LocalDate fallback) {
        return getWrapped().peekLocalDateAt(offset, options, fallback);
    }


    // ----------------------------------------- GROUP: LocalTime -----------------------------------------

    @Override
    public LocalTime getLocalTime(int index, LocalTime fallback) {
        return getWrapped().getLocalTime(index, fallback);
    }

    @Override
    public LocalTime getLocalTime(int index)
            throws ParseException {
        return getWrapped().getLocalTime(index);
    }

    @Override
    public LocalTime peekLocalTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekLocalTimeAt(offset, options);
    }

    @Override
    public LocalTime peekLocalTimeAt(int offset, IDateTimeParseOptions options, LocalTime fallback) {
        return getWrapped().peekLocalTimeAt(offset, options, fallback);
    }


    // ----------------------------------------- GROUP: Instant -----------------------------------------

    @Override
    public Instant getInstant(int index, Instant fallback) {
        return getWrapped().getInstant(index, fallback);
    }

    @Override
    public Instant getInstant(int index)
            throws ParseException {
        return getWrapped().getInstant(index);
    }

    @Override
    public Instant peekInstantAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        return getWrapped().peekInstantAt(offset, options);
    }

    @Override
    public Instant peekInstantAt(int offset, IDateTimeParseOptions options, Instant fallback) {
        return getWrapped().peekInstantAt(offset, options, fallback);
    }


}

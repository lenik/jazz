package net.bodz.bas.repr.path;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.SignStyle;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

import static java.time.temporal.ChronoField.HOUR_OF_DAY;
import static java.time.temporal.ChronoField.MINUTE_OF_HOUR;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import static java.time.temporal.ChronoField.SECOND_OF_MINUTE;

public abstract class AbstractBasicTokenQueue
        implements IBasicTokenQueue,
                   Serializable {

    private static final long serialVersionUID = 1L;

    boolean entered;
    boolean stopped;

    @Override
    public abstract AbstractBasicTokenQueue clone();

    @Override
    public boolean isEntered() {
        return entered;
    }

    public void enter() {
        entered = true;
    }

    public void escape() {
        entered = false;
    }

    @Override
    public boolean isDone() {
        return available() <= 0;
    }

    @Override
    public boolean isStopped() {
        return stopped;
    }

    @Override
    public void stop() {
        this.stopped = true;
    }

    public abstract int position();

    @Override
    public String getRemainingPath() {
        int remaining = available();
        if (remaining == 0)
            return null;

        StringBuilder buf = new StringBuilder(remaining * 20);
        for (int i = 0; i < remaining; i++) {
            if (i != 0)
                buf.append('/');
            buf.append(peekAt(i));
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        int index = position();
        for (int i = 0; i < index; i++) {
            if (i != 0)
                buf.append('/');
            buf.append(get(i));
        }

        // "a/b <---> /c"
        buf.append(" → ");

        int len = available();
        for (int i = index; i < len; i++) {
            buf.append('/');
            buf.append(get(i));
        }
        return buf.toString();
    }

    // ----------------------------------------- GROUP: STRING -----------------------------------------

    @NotNull
    @Override
    public String getString(int index)
            throws ParseException {
        String token = get(index);
        return token;
    }

    @Override
    public String getString(int index, String fallback) {
        String token = get(index);
        return token != null ? token : fallback;
    }

    @Override
    public String peekStringAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return token;
    }

    @Override
    public String peekStringAt(int offset, String fallback) {
        String token = peekAt(offset);
        return token != null ? token : fallback;
    }

    // ----------------------------------------- GROUP: CHAR -----------------------------------------

    @Override
    public char getChar(int index)
            throws ParseException {
        String token = get(index);
        return parseChar(token);
    }

    @Override
    public char getChar(int index, char fallback) {
        String token = get(index);
        if (!isCharLike(token))
            return fallback;
        try {
            return parseChar(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Character peekCharAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseChar(token);
    }

    @Override
    public char peekCharAt(int offset, char fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isCharLike(token))
            return fallback;
        try {
            return parseChar(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isCharLike(String str) {
        return !str.isEmpty();
    }

    static char parseChar(String str)
            throws ParseException {
        if (!isCharLike(str))
            throw new ParseException("Not a char: " + str);
        return str.charAt(0);
    }

    // ----------------------------------------- GROUP: BYTE -----------------------------------------

    @Override
    public byte getByte(int index)
            throws ParseException {
        String token = get(index);
        return parseByte(token);
    }

    @Override
    public byte getByte(int index, byte fallback) {
        String token = get(index);
        if (!isByteLike(token))
            return fallback;
        try {
            return parseByte(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Byte peekByteAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseByte(token);
    }

    @Override
    public byte peekByteAt(int offset, byte fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isByteLike(token))
            return fallback;
        try {
            return Byte.parseByte(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    static boolean isByteLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static byte parseByte(String str)
            throws ParseException {
        if (!isByteLike(str))
            throw new ParseException("Not a byte number: " + str);
        try {
            return Byte.parseByte(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a byte number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: SHORT -----------------------------------------

    @Override
    public short getShort(int index)
            throws ParseException {
        String token = get(index);
        return parseShort(token);
    }

    @Override
    public short getShort(int index, short fallback) {
        String token = get(index);
        if (!isShortLike(token))
            return fallback;
        try {
            return parseShort(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Short peekShortAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseShort(token);
    }

    @Override
    public short peekShortAt(int offset, short fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isShortLike(token))
            return fallback;
        try {
            return Short.parseShort(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    static boolean isShortLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static short parseShort(String str)
            throws ParseException {
        if (!isShortLike(str))
            throw new ParseException("Not a short number: " + str);
        try {
            return Short.parseShort(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a short number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: INT -----------------------------------------

    @Override
    public int getInt(int index)
            throws ParseException {
        String token = get(index);
        return parseInt(token);
    }

    @Override
    public int getInt(int index, int fallback) {
        String token = get(index);
        if (!isIntLike(token))
            return fallback;
        try {
            return parseInt(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Integer peekIntAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseInt(token);
    }

    @Override
    public int peekIntAt(int offset, int fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isIntLike(token))
            return fallback;
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    static boolean isIntLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static int parseInt(String str)
            throws ParseException {
        if (!isIntLike(str))
            throw new ParseException("Not a int number: " + str);
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a int number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: LONG -----------------------------------------

    @Override
    public long getLong(int index)
            throws ParseException {
        String token = get(index);
        return parseLong(token);
    }

    @Override
    public long getLong(int index, long fallback) {
        String token = get(index);
        if (!isLongLike(token))
            return fallback;
        try {
            return parseLong(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Long peekLongAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseLong(token);
    }

    @Override
    public long peekLongAt(int offset, long fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isLongLike(token))
            return fallback;
        try {
            return Long.parseLong(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    static boolean isLongLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static long parseLong(String str)
            throws ParseException {
        if (!isLongLike(str))
            throw new ParseException("Not a long number: " + str);
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a long number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: FLOAT -----------------------------------------

    @Override
    public float getFloat(int index)
            throws ParseException {
        String token = get(index);
        return parseFloat(token);
    }

    @Override
    public float getFloat(int index, float fallback) {
        String token = get(index);
        if (!isFloatLike(token))
            return fallback;
        try {
            return parseFloat(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Float peekFloatAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseFloat(token);
    }

    @Override
    public float peekFloatAt(int offset, float fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isFloatLike(token))
            return fallback;
        try {
            return Float.parseFloat(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    static boolean isFloatLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        // integer part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.') {
                i++;
                break;
            }
            if (c < '0' || c > '9')
                return false;
        }
        // decimal part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.')
                break;
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static float parseFloat(String str)
            throws ParseException {
        if (!isFloatLike(str))
            throw new ParseException("Not a float number: " + str);
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a float number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: DOUBLE -----------------------------------------

    @Override
    public double getDouble(int index)
            throws ParseException {
        String token = get(index);
        return parseDouble(token);
    }

    @Override
    public double getDouble(int index, double fallback) {
        String token = get(index);
        if (!isDoubleLike(token))
            return fallback;
        try {
            return parseDouble(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Double peekDoubleAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseDouble(token);
    }

    @Override
    public double peekDoubleAt(int offset, double fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isDoubleLike(token))
            return fallback;
        try {
            return Double.parseDouble(token);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    static boolean isDoubleLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        // integer part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.') {
                i++;
                break;
            }
            if (c < '0' || c > '9')
                return false;
        }
        // decimal part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.')
                break;
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static double parseDouble(String str)
            throws ParseException {
        if (!isDoubleLike(str))
            throw new ParseException("Not a double number: " + str);
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a double number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: BOOLEAN -----------------------------------------

    @Override
    public boolean getBoolean(int index)
            throws ParseException {
        String token = get(index);
        return parseBoolean(token);
    }

    @Override
    public boolean getBoolean(int index, boolean fallback) {
        String token = get(index);
        if (!isBooleanLike(token))
            return fallback;
        try {
            return parseBoolean(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Boolean peekBooleanAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseBoolean(token);
    }

    @Override
    public boolean peekBooleanAt(int offset, boolean fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isBooleanLike(token))
            return fallback;
        try {
            return parseBoolean(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isBooleanLike(String str) {
        switch (str) {
            case "true":
            case "yes":
            case "on":
            case "1":
            case "t":
            case "y":
            case "false":
            case "no":
            case "off":
            case "0":
            case "f":
            case "n":
                return true;
            default:
                return false;
        }
    }

    static boolean parseBoolean(String str)
            throws ParseException {
        switch (str.toLowerCase()) {
            case "true":
            case "yes":
            case "on":
            case "1":
            case "t":
            case "y":
            case "是":
                return true;
            case "false":
            case "no":
            case "off":
            case "0":
            case "f":
            case "n":
            case "否":
                return false;
            default:
                throw new ParseException("Not a boolean: " + str);
        }
    }

    // ----------------------------------------- GROUP: ENUM -----------------------------------------

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index)
            throws ParseException {
        E val = get(enumType, index, null);
        if (val == null) {
            String token = get(index);
            throw new ParseException(String.format(//
                    "invalid enum name %s, in type %s", token, enumType.getName()));
        }
        return val;
    }

    @Override
    public <E extends Enum<E>> E get(Class<E> enumType, int index, E fallback) {
        String token = get(index);
        if (token == null)
            return fallback;
        try {
            return Enum.valueOf(enumType, token);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset)
            throws ParseException {
        E val = peekAt(enumType, offset, null);
        if (val == null) {
            String token = peekAt(offset);
            throw new ParseException(String.format(//
                    "invalid enum name %s, in type %s", token, enumType.getName()));
        }
        return val;
    }

    @Override
    public <E extends Enum<E>> E peekAt(Class<E> enumType, int offset, E fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        try {
            return Enum.valueOf(enumType, token);
        } catch (IllegalArgumentException e) {
            return fallback;
        }
    }

    // ----------------------------------------- GROUP: BigInteger -----------------------------------------

    @Override
    public BigInteger getBigInteger(int index)
            throws ParseException {
        String token = get(index);
        return parseBigInteger(token);
    }

    @Override
    public BigInteger getBigInteger(int index, BigInteger fallback) {
        String token = get(index);
        if (!isBigIntegerLike(token))
            return fallback;
        try {
            return parseBigInteger(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public BigInteger peekBigIntegerAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseBigInteger(token);
    }

    @Override
    public BigInteger peekBigIntegerAt(int offset, BigInteger fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isBigIntegerLike(token))
            return fallback;
        try {
            return parseBigInteger(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isBigIntegerLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        // integer part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static BigInteger parseBigInteger(String str)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isBigIntegerLike(str))
            throw new ParseException("Not a BigInteger number: " + str);
        try {
            return new BigInteger(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a BigInteger number: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: BigDecimal -----------------------------------------

    @Override
    public BigDecimal getBigDecimal(int index)
            throws ParseException {
        String token = get(index);
        return parseBigDecimal(token);
    }

    @Override
    public BigDecimal getBigDecimal(int index, BigDecimal fallback) {
        String token = get(index);
        if (!isBigDecimalLike(token))
            return fallback;
        try {
            return parseBigDecimal(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public BigDecimal peekBigDecimalAt(int offset)
            throws ParseException {
        String token = peekAt(offset);
        return parseBigDecimal(token);
    }

    @Override
    public BigDecimal peekBigDecimalAt(int offset, BigDecimal fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isBigDecimalLike(token))
            return fallback;
        try {
            return parseBigDecimal(token);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isBigDecimalLike(String str) {
        int len = str.length();
        if (len == 0)
            return false;
        int i = 0;
        if (str.charAt(i) == '-')
            i++;
        // integer part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.') {
                i++;
                break;
            }
            if (c < '0' || c > '9')
                return false;
        }
        // decimal part
        for (; i < len; i++) {
            char c = str.charAt(i);
            if (c == '.')
                break;
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }

    static BigDecimal parseBigDecimal(String str)
            throws ParseException {
        if (str.isEmpty())
            return null;
        BigDecimal scale = null;
        if (str.endsWith("%")) {
            scale = new BigDecimal("0.01");
            str = str.substring(0, str.length() - 1);
        }

        if (!isBigDecimalLike(str))
            throw new ParseException("Not a BigDecimal number: " + str);

        BigDecimal decimal;
        try {
            decimal = new BigDecimal(str);
        } catch (NumberFormatException e) {
            throw new ParseException("Not a BigDecimal number: " + str, e);
        }

        if (scale != null)
            decimal = decimal.multiply(scale);
        return decimal;
    }

    // ----------------------------------------- GROUP: ZonedDateTime -----------------------------------------

    @Override
    public ZonedDateTime getZonedDateTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseZonedDateTime(token, options);
    }

    @Override
    public ZonedDateTime getZonedDateTime(int index, IDateTimeParseOptions options, ZonedDateTime fallback) {
        String token = get(index);
        if (!isZonedDateTimeLike(token, options))
            return fallback;
        try {
            return parseZonedDateTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public ZonedDateTime peekZonedDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseZonedDateTime(token, options);
    }

    @Override
    public ZonedDateTime peekZonedDateTimeAt(int offset, IDateTimeParseOptions options, ZonedDateTime fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isZonedDateTimeLike(token, options))
            return fallback;
        try {
            return parseZonedDateTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isZonedDateTimeLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static ZonedDateTime parseZonedDateTime(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isZonedDateTimeLike(str, options))
            throw new ParseException("Not a ZonedDateTime temporal: " + str);

        boolean strict = true;
        if (options != null)
            if (!Parsers.haveDateTime(str))
                strict = false;

        if (strict)
            try {
                return ZonedDateTime.parse(str);
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a ZonedDateTime temporal: " + str, e);
            }

        // partial
        if (str.indexOf(':') != -1) {
            // local time only
            try {
                LocalTime localTime = LocalTime.parse(str);
                LocalDate defaultDate = options.getDefaultDate();
                return localTime.atDate(defaultDate).atZone(ZoneId.systemDefault());
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a LocalTime temporal: " + str, e);
            }
        } else {
            // local date only
            try {
                LocalDate localDate = LocalDate.parse(str);
                LocalTime defaultTime = options.getDefaultTime();
                return localDate.atTime(defaultTime).atZone(ZoneId.systemDefault());
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a LocalDate temporal: " + str, e);
            }
        }
    }

    // ----------------------------------------- GROUP: OffsetDateTime -----------------------------------------

    @Override
    public OffsetDateTime getOffsetDateTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseOffsetDateTime(token, options);
    }

    @Override
    public OffsetDateTime getOffsetDateTime(int index, IDateTimeParseOptions options, OffsetDateTime fallback) {
        String token = get(index);
        if (!isOffsetDateTimeLike(token, options))
            return fallback;
        try {
            return parseOffsetDateTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public OffsetDateTime peekOffsetDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseOffsetDateTime(token, options);
    }

    @Override
    public OffsetDateTime peekOffsetDateTimeAt(int offset, IDateTimeParseOptions options, OffsetDateTime fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isOffsetDateTimeLike(token, options))
            return fallback;
        try {
            return parseOffsetDateTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isOffsetDateTimeLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static OffsetDateTime parseOffsetDateTime(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isOffsetDateTimeLike(str, options))
            throw new ParseException("Not a OffsetDateTime temporal: " + str);

        boolean strict = true;
        if (options != null) {
            if (!Parsers.haveDateTime(str))
                strict = false;
        }

        if (strict)
            try {
                return OffsetDateTime.parse(str);
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a OffsetDateTime temporal: " + str, e);
            }

        // partial
        if (str.indexOf(':') != -1) {
            // local time only
            try {
                OffsetTime offsetTime = OffsetTime.parse(str);
                LocalDate defaultDate = options.getDefaultDate();
                return offsetTime.atDate(defaultDate);
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a OffsetTime temporal: " + str, e);
            }
        } else {
            // local date only
            try {
                LocalDate localDate = LocalDate.parse(str);
                LocalTime defaultTime = options.getDefaultTime();
                LocalDateTime localDateTime = localDate.atTime(defaultTime);
                return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a LocalDate temporal: " + str, e);
            }
        }

    }

    // ----------------------------------------- GROUP: OffsetTime -----------------------------------------

    @Override
    public OffsetTime getOffsetTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseOffsetTime(token, options);
    }

    @Override
    public OffsetTime getOffsetTime(int index, IDateTimeParseOptions options, OffsetTime fallback) {
        String token = get(index);
        if (!isOffsetTimeLike(token, options))
            return fallback;
        try {
            return parseOffsetTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public OffsetTime peekOffsetTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseOffsetTime(token, options);
    }

    @Override
    public OffsetTime peekOffsetTimeAt(int offset, IDateTimeParseOptions options, OffsetTime fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isOffsetTimeLike(token, options))
            return fallback;
        try {
            return parseOffsetTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isOffsetTimeLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static OffsetTime parseOffsetTime(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isOffsetTimeLike(str, options))
            throw new ParseException("Not a OffsetTime temporal: " + str);

        str = Parsers.timePart(str);
        try {
            return OffsetTime.parse(str);
        } catch (DateTimeParseException e) {
            throw new ParseException("Not a OffsetTime temporal: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: LocalDateTime -----------------------------------------

    @Override
    public LocalDateTime getLocalDateTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseLocalDateTime(token, options);
    }

    @Override
    public LocalDateTime getLocalDateTime(int index, IDateTimeParseOptions options, LocalDateTime fallback) {
        String token = get(index);
        if (!isLocalDateTimeLike(token, options))
            return fallback;
        try {
            return parseLocalDateTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public LocalDateTime peekLocalDateTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseLocalDateTime(token, options);
    }

    @Override
    public LocalDateTime peekLocalDateTimeAt(int offset, IDateTimeParseOptions options, LocalDateTime fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isLocalDateTimeLike(token, options))
            return fallback;
        try {
            return LocalDateTime.parse(token);
        } catch (DateTimeParseException e) {
            return fallback;
        }
    }

    static boolean isLocalDateTimeLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static LocalDateTime parseLocalDateTime(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isLocalDateTimeLike(str, options))
            throw new ParseException("Not a LocalDateTime temporal: " + str);

        boolean strict = true;
        if (options != null)
            if (!Parsers.haveDateTime(str))
                strict = false;

        if (strict)
            try {
                return LocalDateTime.parse(str);
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a LocalDateTime temporal: " + str, e);
            }

        // partial
        if (str.indexOf(':') != -1) {
            // local time only
            try {
                LocalTime localTime = parseLocalTime(str, options);
                assert localTime != null;
                LocalDate defaultDate = options.getDefaultDate();
                return localTime.atDate(defaultDate);
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a LocalTime temporal: " + str, e);
            }
        } else {
            // local date only
            try {
                LocalDate localDate = parseLocalDate(str, options);
                assert localDate != null;
                LocalTime defaultTime = options.getDefaultTime();
                return localDate.atTime(defaultTime);
            } catch (DateTimeParseException e) {
                throw new ParseException("Not a LocalDate temporal: " + str, e);
            }
        }
    }

    // ----------------------------------------- GROUP: LocalDate -----------------------------------------

    @Override
    public LocalDate getLocalDate(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseLocalDate(token, options);
    }

    @Override
    public LocalDate getLocalDate(int index, IDateTimeParseOptions options, LocalDate fallback) {
        String token = get(index);
        if (!isLocalDateLike(token, options))
            return fallback;
        try {
            return parseLocalDate(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public LocalDate peekLocalDateAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseLocalDate(token, options);
    }

    @Override
    public LocalDate peekLocalDateAt(int offset, IDateTimeParseOptions options, LocalDate fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isLocalDateLike(token, options))
            return fallback;
        try {
            return parseLocalDate(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isLocalDateLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static LocalDate parseLocalDate(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isLocalDateLike(str, options))
            throw new ParseException("Not a LocalDate temporal: " + str);

        str = Parsers.datePart(str);
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new ParseException("Not a LocalDate temporal: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: LocalTime -----------------------------------------

    @Override
    public LocalTime getLocalTime(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseLocalTime(token, options);
    }

    @Override
    public LocalTime getLocalTime(int index, IDateTimeParseOptions options, LocalTime fallback) {
        String token = get(index);
        if (!isLocalTimeLike(token, options))
            return fallback;
        try {
            return parseLocalTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public LocalTime peekLocalTimeAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseLocalTime(token, options);
    }

    @Override
    public LocalTime peekLocalTimeAt(int offset, IDateTimeParseOptions options, LocalTime fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isLocalTimeLike(token, options))
            return fallback;
        try {
            return parseLocalTime(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isLocalTimeLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static DateTimeFormatter LOCAL_TIME;

    static {
        LOCAL_TIME = new DateTimeFormatterBuilder() //
                .appendValue(HOUR_OF_DAY, 1, 2, SignStyle.NORMAL)//
                .appendLiteral(':')//
                .appendValue(MINUTE_OF_HOUR, 1, 2, SignStyle.NORMAL)//

                .optionalStart()//
                .appendLiteral('.')//
                .appendValue(SECOND_OF_MINUTE, 1, 2, SignStyle.NORMAL)//
                .optionalEnd()//

                .optionalStart()//
                .appendLiteral(':')//
                .appendValue(SECOND_OF_MINUTE, 1, 2, SignStyle.NORMAL)//
                .optionalStart()//
                .appendFraction(NANO_OF_SECOND, 0, 9, true)//
                .optionalEnd()//
                .optionalEnd()//

                .toFormatter();
    }

    static LocalTime parseLocalTime(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;

        str = Parsers.timePart(str);

        // PATCH: Handle exception: HH:mm.ss
        int lastDot = str.lastIndexOf('.');
        if (lastDot != -1) {
            int colon = str.indexOf(':');
            if (colon != -1) {
                int colon2 = str.indexOf(':', colon + 1);
                if (colon2 == -1) {
                    str = str.substring(0, lastDot) + ":" + str.substring(lastDot + 1);
                }
            }
        }

        if (!isLocalTimeLike(str, options))
            throw new ParseException("Not a LocalTime temporal: " + str);

        try {
            return LOCAL_TIME.parse(str, LocalTime::from);
        } catch (DateTimeParseException e) {
            throw new ParseException("Not a LocalTime temporal: " + str, e);
        }
    }

    // ----------------------------------------- GROUP: Instant -----------------------------------------

    @Override
    public Instant getInstant(int index, IDateTimeParseOptions options)
            throws ParseException {
        String token = get(index);
        return parseInstant(token, options);
    }

    @Override
    public Instant getInstant(int index, IDateTimeParseOptions options, Instant fallback) {
        String token = get(index);
        if (!isInstantLike(token, options))
            return fallback;
        try {
            return parseInstant(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    @Override
    public Instant peekInstantAt(int offset, IDateTimeParseOptions options)
            throws ParseException {
        String token = peekAt(offset);
        return parseInstant(token, options);
    }

    @Override
    public Instant peekInstantAt(int offset, IDateTimeParseOptions options, Instant fallback) {
        String token = peekAt(offset);
        if (token == null)
            return fallback;
        if (!isInstantLike(token, options))
            return fallback;
        try {
            return parseInstant(token, options);
        } catch (ParseException e) {
            return fallback;
        }
    }

    static boolean isInstantLike(String str, IDateTimeParseOptions options) {
        return true;
    }

    static Instant parseInstant(String str, IDateTimeParseOptions options)
            throws ParseException {
        if (str.isEmpty())
            return null;
        if (!isInstantLike(str, options))
            throw new ParseException("Not a Instant temporal: " + str);
        try {
            return Instant.parse(str);
        } catch (DateTimeParseException e) {
            throw new ParseException("Not a Instant temporal: " + str, e);
        }
    }

}

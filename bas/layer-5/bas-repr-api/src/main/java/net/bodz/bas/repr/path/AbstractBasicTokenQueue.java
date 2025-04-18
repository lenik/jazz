package net.bodz.bas.repr.path;

import java.io.Serializable;

import net.bodz.bas.err.ParseException;

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
            return Boolean.parseBoolean(token);
        } catch (NumberFormatException e) {
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
        switch (str) {
            case "true":
            case "yes":
            case "on":
            case "1":
            case "t":
            case "y":
                return true;
            case "false":
            case "no":
            case "off":
            case "0":
            case "f":
            case "n":
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

    // ----------------------------------------- GROUP end -----------------------------------------

    public abstract int position();

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

}

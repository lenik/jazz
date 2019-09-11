package net.bodz.bas.fmt.rst;

import net.bodz.bas.c.enm.Enums;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.StringNum;
import net.bodz.bas.c.string.StringStat;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;

public class DataCodec
        implements IDataCodec {

    @Override
    public final byte parseByte(String data)
            throws ParseException {
        return parseByte(null, data);
    }

    @Override
    public final short parseShort(String data)
            throws ParseException {
        return parseShort(null, data);
    }

    @Override
    public final int parseInt(String data)
            throws ParseException {
        return parseInt(null, data);
    }

    @Override
    public final long parseLong(String data)
            throws ParseException {
        return parseLong(null, data);
    }

    @Override
    public final float parseFloat(String data)
            throws ParseException {
        return parseFloat(null, data);
    }

    @Override
    public final double parseDouble(String data)
            throws ParseException {
        return parseDouble(null, data);
    }

    @Override
    public final boolean parseBool(String data)
            throws ParseException {
        return parseBool(null, data);
    }

    @Override
    public final char parseChar(String data)
            throws ParseException {
        return parseChar(null, data);
    }

    @Override
    public String parseString(String data)
            throws ParseException {
        return parseString(null, data);
    }

    @Override
    public <T extends Enum<T>> T parseEnum(Class<T> type, String data)
            throws ParseException {
        return parseEnum(null, type, data);
    }

    @Override
    public final byte[] parseBytes(String data)
            throws ParseException {
        return parseBytes(null, data);
    }

    @Override
    public final int parseBytes(String data, byte[] buf, int off, int maxLen)
            throws ParseException {
        return parseBytes(null, data, buf, off, maxLen);
    }

    @Override
    public final short[] parseShorts(String data)
            throws ParseException {
        return parseShorts(null, data);
    }

    @Override
    public final int parseShorts(String data, short[] buf, int off, int maxLen)
            throws ParseException {
        return parseShorts(null, data, buf, off, maxLen);
    }

    @Override
    public final int[] parseInts(String data)
            throws ParseException {
        return parseInts(null, data);
    }

    @Override
    public final int parseInts(String data, int[] buf, int off, int maxLen)
            throws ParseException {
        return parseInts(null, data, buf, off, maxLen);
    }

    @Override
    public final long[] parseLongs(String data)
            throws ParseException {
        return parseLongs(null, data);
    }

    @Override
    public final int parseLongs(String data, long[] buf, int off, int maxLen)
            throws ParseException {
        return parseLongs(null, data, buf, off, maxLen);
    }

    @Override
    public final float[] parseFloats(String data)
            throws ParseException {
        return parseFloats(null, data);
    }

    @Override
    public final int parseFloats(String data, float[] buf, int off, int maxLen)
            throws ParseException {
        return parseFloats(null, data, buf, off, maxLen);
    }

    @Override
    public final double[] parseDoubles(String data)
            throws ParseException {
        return parseDoubles(null, data);
    }

    @Override
    public final int parseDoubles(String data, double[] buf, int off, int maxLen)
            throws ParseException {
        return parseDoubles(null, data, buf, off, maxLen);
    }

    @Override
    public final boolean[] parseBools(String data)
            throws ParseException {
        return parseBools(null, data);
    }

    @Override
    public final int parseBools(String data, boolean[] buf, int off, int maxLen)
            throws ParseException {
        return parseBools(null, data, buf, off, maxLen);
    }

    @Override
    public final char[] parseChars(String data)
            throws ParseException {
        return parseChars(null, data);
    }

    @Override
    public final int parseChars(String data, char[] buf, int off, int maxLen)
            throws ParseException {
        return parseChars(null, data, buf, off, maxLen);
    }

    @Override
    public final String[] parseStrings(String data)
            throws ParseException {
        return parseStrings(null, data);
    }

    @Override
    public final int parseStrings(String data, String[] buf, int off, int maxLen)
            throws ParseException {
        return parseStrings(null, data, buf, off, maxLen);
    }

    @Override
    public byte parseByte(String field, String data)
            throws ParseException {
        try {
            return StringNum.parseByte(data, 0);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid byte: " + data, e);
        }
    }

    @Override
    public short parseShort(String field, String data)
            throws ParseException {
        try {
            return StringNum.parseShort(data, 0);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid short: " + data, e);
        }
    }

    @Override
    public int parseInt(String field, String data)
            throws ParseException {
        try {
            return StringNum.parseInt(data, 0);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid int: " + data, e);
        }
    }

    @Override
    public long parseLong(String field, String data)
            throws ParseException {
        try {
            return StringNum.parseLong(data, 0);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid long: " + data, e);
        }
    }

    @Override
    public float parseFloat(String field, String data)
            throws ParseException {
        try {
            return Float.parseFloat(data);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid float: " + data, e);
        }
    }

    @Override
    public double parseDouble(String field, String data)
            throws ParseException {
        try {
            return Double.parseDouble(data);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid double: " + data, e);
        }
    }

    @Override
    public boolean parseBool(String field, String data)
            throws ParseException {
        try {
            return Boolean.parseBoolean(data);
        } catch (NumberFormatException e) {
            throw new ParseException(field, "invalid boolean: " + data, e);
        }
    }

    @Override
    public char parseChar(String field, String data)
            throws ParseException {
        String str = parseString(field, data);
        if (str.isEmpty())
            return '\0';
        else
            return str.charAt(0);
    }

    @Override
    public String parseString(String field, String data)
            throws ParseException {

        if (data == null)
            return null;
        if ("null".equals(data))
            return null;

        String str;
        try {
            str = StringEscape.parseQuotedJavaString(data);
        } catch (ParseException e) {
            throw new ParseException(field, "invalid string: " + data, e);
        }
        if (str == null)
            throw new ParseException(field, "unquoted string: " + data);
        return str;
    }

// @Override
    @Override
    public <T extends Enum<T>> T parseEnum(String field, Class<T> clazz, String data)
            throws ParseException {
        if (clazz == null)
            throw new NullPointerException("clazz");
        if (data == null || data.isEmpty())
            return null;
        return Enums.getEnum(clazz, data);
    }

    @Override
    public byte[] parseBytes(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        byte[] buf = new byte[n];
        parseBytes(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseBytes(String field, String data, byte[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                byte val = StringNum.parseByte(word, 0);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid byte: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public short[] parseShorts(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        short[] buf = new short[n];
        parseShorts(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseShorts(String field, String data, short[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                short val = StringNum.parseShort(word, 0);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid short: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public int[] parseInts(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        int[] buf = new int[n];
        parseInts(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseInts(String field, String data, int[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                int val = StringNum.parseInt(word, 0);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid int: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public long[] parseLongs(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        long[] buf = new long[n];
        parseLongs(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseLongs(String field, String data, long[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                long val = StringNum.parseLong(word, 0);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid long: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public float[] parseFloats(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        float[] buf = new float[n];
        parseFloats(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseFloats(String field, String data, float[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                float val = Float.parseFloat(word);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid float: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public double[] parseDoubles(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        double[] buf = new double[n];
        parseDoubles(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseDoubles(String field, String data, double[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                double val = Double.parseDouble(word);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid double: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public boolean[] parseBools(String field, String data)
            throws ParseException {
        int n = StringStat.wordCount(data);
        boolean[] buf = new boolean[n];
        parseBools(field, data, buf, 0, n);
        return buf;
    }

    @Override
    public int parseBools(String field, String data, boolean[] buf, int off, int maxLen)
            throws ParseException {
        int index = 0;
        for (String word : StringArray.extractWords(data, maxLen)) {
            try {
                boolean val = Boolean.parseBoolean(word);
                buf[off++] = val;
            } catch (NumberFormatException e) {
                throw new ParseException(field + "[" + index + "]", "invalid boolean: " + word, e);
            }
            index++;
        }
        return index;
    }

    @Override
    public char[] parseChars(String field, String data)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public int parseChars(String field, String data, char[] buf, int off, int maxLen)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public String[] parseStrings(String field, String data)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public int parseStrings(String field, String data, String[] buf, int off, int maxLen)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public <T extends Enum<T>> T parseEnums(Class<?> enmType, String field, String data)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public int[] parseEnums(Class<?> enmType, String field, String data, String[] buf, int off, int maxLen)
            throws ParseException {
        throw new NotImplementedException();
    }

}

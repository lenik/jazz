package net.bodz.bas.fmt.rst;

import net.bodz.bas.err.ParseException;

public interface IDataCodec {

    byte parseByte(String data)
            throws ParseException;

    short parseShort(String data)
            throws ParseException;

    int parseInt(String data)
            throws ParseException;

    long parseLong(String data)
            throws ParseException;

    float parseFloat(String data)
            throws ParseException;

    double parseDouble(String data)
            throws ParseException;

    boolean parseBool(String data)
            throws ParseException;

    char parseChar(String data)
            throws ParseException;

    String parseString(String data)
            throws ParseException;

    <T extends Enum<T>> T parseEnum(Class<T> type, String data)
            throws ParseException;

    byte[] parseBytes(String data)
            throws ParseException;

    int parseBytes(String data, byte[] buf, int off, int maxLen)
            throws ParseException;

    short[] parseShorts(String data)
            throws ParseException;

    int parseShorts(String data, short[] buf, int off, int maxLen)
            throws ParseException;

    int[] parseInts(String data)
            throws ParseException;

    int parseInts(String data, int[] buf, int off, int maxLen)
            throws ParseException;

    long[] parseLongs(String data)
            throws ParseException;

    int parseLongs(String data, long[] buf, int off, int maxLen)
            throws ParseException;

    float[] parseFloats(String data)
            throws ParseException;

    int parseFloats(String data, float[] buf, int off, int maxLen)
            throws ParseException;

    double[] parseDoubles(String data)
            throws ParseException;

    int parseDoubles(String data, double[] buf, int off, int maxLen)
            throws ParseException;

    boolean[] parseBools(String data)
            throws ParseException;

    int parseBools(String data, boolean[] buf, int off, int maxLen)
            throws ParseException;

    char[] parseChars(String data)
            throws ParseException;

    int parseChars(String data, char[] buf, int off, int maxLen)
            throws ParseException;

    String[] parseStrings(String data)
            throws ParseException;

    int parseStrings(String data, String[] buf, int off, int maxLen)
            throws ParseException;

    byte parseByte(String field, String data)
            throws ParseException;

    short parseShort(String field, String data)
            throws ParseException;

    int parseInt(String field, String data)
            throws ParseException;

    long parseLong(String field, String data)
            throws ParseException;

    float parseFloat(String field, String data)
            throws ParseException;

    double parseDouble(String field, String data)
            throws ParseException;

    boolean parseBool(String field, String data)
            throws ParseException;

    char parseChar(String field, String data)
            throws ParseException;

    String parseString(String field, String data)
            throws ParseException;

    <T extends Enum<T>> T parseEnum(String field, Class<T> clazz, String data)
            throws ParseException;

    byte[] parseBytes(String field, String data)
            throws ParseException;

    int parseBytes(String field, String data, byte[] buf, int off, int maxLen)
            throws ParseException;

    short[] parseShorts(String field, String data)
            throws ParseException;

    int parseShorts(String field, String data, short[] buf, int off, int maxLen)
            throws ParseException;

    int[] parseInts(String field, String data)
            throws ParseException;

    int parseInts(String field, String data, int[] buf, int off, int maxLen)
            throws ParseException;

    long[] parseLongs(String field, String data)
            throws ParseException;

    int parseLongs(String field, String data, long[] buf, int off, int maxLen)
            throws ParseException;

    float[] parseFloats(String field, String data)
            throws ParseException;

    int parseFloats(String field, String data, float[] buf, int off, int maxLen)
            throws ParseException;

    double[] parseDoubles(String field, String data)
            throws ParseException;

    int parseDoubles(String field, String data, double[] buf, int off, int maxLen)
            throws ParseException;

    boolean[] parseBools(String field, String data)
            throws ParseException;

    int parseBools(String field, String data, boolean[] buf, int off, int maxLen)
            throws ParseException;

    char[] parseChars(String field, String data)
            throws ParseException;

    int parseChars(String field, String data, char[] buf, int off, int maxLen)
            throws ParseException;

    String[] parseStrings(String field, String data)
            throws ParseException;

    int parseStrings(String field, String data, String[] buf, int off, int maxLen)
            throws ParseException;

    <T extends Enum<T>> T parseEnums(Class<?> enmType, String field, String data)
            throws ParseException;

    int[] parseEnums(Class<?> enmType, String field, String data, String[] buf, int off, int maxLen)
            throws ParseException;

}

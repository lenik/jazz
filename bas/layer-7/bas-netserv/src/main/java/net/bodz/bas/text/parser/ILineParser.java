package net.bodz.bas.text.parser;

import java.nio.charset.StandardCharsets;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Interface for a line parser that can process lines of data.
 */
public interface ILineParser<T> {

    /**
     * Parses a line from a byte array, starting at the beginning and ending at the end of the array.
     *
     * @param line The byte array containing the line to be parsed.
     * @throws ParseException If an error occurs during parsing.
     */
    default T parseLine(@NotNull byte[] line)
            throws ParseException {
        return parseLine(line, 0, line.length);
    }

    /**
     * Parses a line from a byte array within the specified range.
     *
     * @param line The byte array containing the line to be parsed.
     * @param off  The starting index of the subarray to parse.
     * @param len  The length of the subarray to parse.
     * @throws ParseException If an error occurs during parsing.
     */
    default T parseLine(@NotNull byte[] line, int off, int len)
            throws ParseException {
        String s = new String(line, off, len, StandardCharsets.UTF_8);
        return parseLine(s);
    }

    default T parseLine(@NotNull char[] line)
            throws ParseException {
        return parseLine(line, 0, line.length);
    }

    default T parseLine(@NotNull char[] line, int off, int len)
            throws ParseException {
        return parseLine(new String(line, off, len));
    }

    /**
     * Parses a string as if it were a line of data.
     *
     * @param line The string to be parsed.
     * @throws ParseException If an error occurs during parsing.
     */
    T parseLine(@NotNull String line)
            throws ParseException;

}
package net.bodz.bas.parser;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.ILookAhead;
import net.bodz.bas.io.NoLookAhead;
import net.bodz.bas.meta.decl.NotNull;

/**
 * Interface for a buffered parser that can process data in chunks.
 */
public interface IBufferedParser {

    /**
     * Adds an octet to the buffer.
     *
     * @param octet The byte value to be added to the buffer.
     */
    void putOctet(int octet);

    /**
     * Adds a sequence of octets to the buffer.
     *
     * @param octets The array containing the octets to be added to the buffer.
     * @param off    The start offset in the data.
     * @param len    The number of bytes to process.
     */
    default void putOctet(byte[] octets, int off, int len) {
        int pos = off;
        for (int i = 0; i < len; i++)
            putOctet(octets[pos++] & 0xFF);
    }

    /**
     * Gets the current length of the unparsed buffer.
     *
     * @return The number of bytes currently in the buffer.
     */
    int getBufferLength();

    /**
     * Checks if there are any bytes in the unparsed buffer.
     *
     * @return True if the buffer is not empty, false otherwise.
     */
    default boolean isBuffered() {
        return getBufferLength() != 0;
    }

    /**
     * Clears the unparsed buffer by removing all its contents.
     */
    void resetBuffer();

    /**
     * Copies the current content of the unparsed buffer into a new array.
     *
     * @return A copy of the buffer's content.
     */
    byte[] bufferCopy();

    /**
     * Converts the unparsed buffer contents to a string using a specified charset.
     *
     * @param charset The character set to be used for conversion.
     * @return The buffer contents as a string in the given charset.
     */
    default String bufferText(@NotNull Charset charset) {
        byte[] bin = bufferCopy();
        return new String(bin, charset);
    }

    /**
     * Converts the buffer contents to a string using a specified encoding.
     *
     * @param encoding The name of the character encoding to be used for conversion.
     * @return The buffer contents as a string in the given encoding.
     * @throws IllegalArgumentException If the specified encoding is not supported.
     */
    default String bufferText(@NotNull String encoding) {
        byte[] bin = bufferCopy();
        try {
            return new String(bin, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Gets a copy of the unparsed buffer contents as an array of bytes.
     *
     * @return A copy of the unparsed buffer's content as an array of bytes.
     */
    default byte[] getBufferBin() {
        return bufferCopy();
    }

    /**
     * Converts the buffer contents to a string using UTF-8 encoding.
     *
     * @return The buffer contents as a string in UTF-8 format.
     */
    default String getBufferText() {
        return bufferText(StandardCharsets.UTF_8);
    }

    /**
     * Parses the buffer, removing any parsed text from the buffer and leaving unparsed or error-parsed text in it.
     *
     * @param la The lookahead object to be used for parsing.
     * @throws ParseException If an error occurs during parsing.
     */
    void parse(@NotNull ILookAhead la)
            throws ParseException;

    /**
     * Parses the buffer without lookahead, removing any parsed text from the buffer and leaving unparsed or
     * error-parsed text in it.
     *
     * @throws ParseException If an error occurs during parsing.
     */
    default void parse()
            throws ParseException {
        parse(NoLookAhead.INSTANCE);
    }

}

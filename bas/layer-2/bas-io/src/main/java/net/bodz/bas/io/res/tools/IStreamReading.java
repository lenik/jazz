package net.bodz.bas.io.res.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import java.util.List;

import net.bodz.bas.err.RuntimeIOException;
import net.bodz.bas.t.iterator.immed.Mitorx;
import net.bodz.bas.t.object.IContextUtility;

public interface IStreamReading
        extends IContextUtility {

    @Override
    IStreamReading clone();

    OpenOption[] getOpenOptions();

    IStreamReading setOpenOptions(OpenOption... options);

    /**
     * Read all bytes.
     * 
     * @exception FileNotFoundException
     *                If file doesn't exist.
     * @throws OutOfMemoryError
     *             If the contents is too large to read.
     */
    byte[] read()
            throws IOException, OutOfMemoryError;

    /**
     * @return non-<code>null</code> byte array containing the read contents.
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    byte[] read(int maxBytesToRead)
            throws IOException;

    /**
     * @return non-<code>null</code> char array containing the read contents.
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    char[] readChars()
            throws IOException;

    /**
     * @return non-<code>null</code> char array containing the read contents.
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    char[] readChars(int maxCharsToRead)
            throws IOException;

    /**
     * Read the entire contents.
     * 
     * @exception FileNotFoundException
     *                If file doesn't exist.
     * 
     * @throws OutOfMemoryError
     *             If the contents is too large to read.
     */
    String readString()
            throws IOException, OutOfMemoryError;

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    Mitorx<byte[], ? extends IOException> blocks(boolean allowOverlap)
            throws IOException;

    /**
     * @return Overlap-allowed{@link Iterable} which may throw {@link RuntimeIOException}.
     */
    Iterable<byte[]> blocks()
            throws IOException;

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    List<byte[]> readBlocks(int maxBlocks)
            throws IOException;

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    Mitorx<char[], ? extends IOException> charBlocks(boolean allowOverlap)
            throws IOException;

    /**
     * @return Overlap-allowed{@link Iterable} which may throw {@link RuntimeIOException}.
     */
    Iterable<char[]> charBlocks();

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    List<char[]> readCharBlocks(int maxBlocks)
            throws IOException;

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     * @param allowOverlap
     *            Unused.
     */
    Mitorx<String, ? extends IOException> _lines(boolean chopped)
            throws IOException;

    /**
     * Iterate each line without chopping.
     * 
     * @see #lines(boolean)
     */
    Iterable<String> lines();

    /**
     * @return Overlap-allowed {@link Iterable} which may throw {@link RuntimeIOException}.
     */
    Iterable<String> lines(boolean chopped);

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    List<String> readLines()
            throws IOException;

    /**
     * @exception FileNotFoundException
     *                If file doesn't exist.
     */
    List<String> readLines(boolean chopped, int maxLines)
            throws IOException;

    byte[] digest(MessageDigest digest)
            throws IOException;

    byte[] md5()
            throws IOException;

    byte[] sha1()
            throws IOException;

}

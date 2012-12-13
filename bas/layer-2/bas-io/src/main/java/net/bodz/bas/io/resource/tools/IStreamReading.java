package net.bodz.bas.io.resource.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import net.bodz.bas.err.RuntimeIOException;
import net.bodz.bas.t.iterator.immed.Mitorx;

public interface IStreamReading
        extends Cloneable {

    IStreamReading clone();

    int getBlockSize();

    /**
     * @return this self.
     */
    IStreamReading setBlockSize(int blockSize);

    /**
     * Read the entire contents.
     * 
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     * @throws OutOfMemoryError
     *             If the contents is too large to read.
     */
    byte[] readBinaryContents()
            throws IOException, OutOfMemoryError;

    /**
     * Read the entire contents.
     * 
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     * @throws OutOfMemoryError
     *             If the contents is too large to read.
     */
    String readTextContents()
            throws IOException, OutOfMemoryError;

    /**
     * @return non-<code>null</code> byte array containing the read contents.
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    byte[] readBytes(int maxBytesToRead)
            throws IOException;

    /**
     * @return non-<code>null</code> char array containing the read contents.
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    char[] readChars(int maxCharsToRead)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    Mitorx<byte[], ? extends IOException> byteBlocks(boolean allowOverlap)
            throws IOException;

    /**
     * @return Overlap-allowed{@link Iterable} which may throw {@link RuntimeIOException}.
     */
    Iterable<byte[]> byteBlocks()
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    List<byte[]> listByteBlocks(int maxBlocks)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    Mitorx<char[], ? extends IOException> charBlocks(boolean allowOverlap)
            throws IOException;

    /**
     * @return Overlap-allowed{@link Iterable} which may throw {@link RuntimeIOException}.
     */
    Iterable<char[]> charBlocks();

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    List<char[]> listCharBlocks(int maxBlocks)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @param allowOverlap
     *            Unused.
     * @throws IOException
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
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    List<String> listLines()
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    List<String> listLines(boolean chopped, int maxLines)
            throws IOException;

    public byte[] digest(MessageDigest digest)
            throws IOException;

    public byte[] md5()
            throws IOException;

    public byte[] sha1()
            throws IOException;

}

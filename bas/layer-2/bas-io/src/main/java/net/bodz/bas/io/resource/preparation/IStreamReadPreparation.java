package net.bodz.bas.io.resource.preparation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.collection.iterator.IterableX;
import net.bodz.bas.collection.iterator.IteratorTargetException;

public interface IStreamReadPreparation
        extends Cloneable {

    IStreamReadPreparation clone();

    int getBlockSize();

    /**
     * @return this self.
     */
    IStreamReadPreparation setBlockSize(int blockSize);

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
    IteratorMX<byte[], ? extends IOException> byteBlocks(boolean allowOverlap)
            throws IOException;

    /**
     * @return Overlap-allowed{@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<byte[], IOException> byteBlocks()
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
    IteratorMX<char[], ? extends IOException> charBlocks(boolean allowOverlap)
            throws IOException;

    /**
     * @return Overlap-allowed{@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<char[], IOException> charBlocks()
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    List<char[]> listCharBlocks(int maxBlocks)
            throws IOException;

    /**
     * @param allowOverlap
     *            Unused.
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    IteratorMX<String, ? extends IOException> lines(boolean allowOverlap, boolean chopped)
            throws IOException;

    /**
     * @return Overlap-allowed {@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<String, IOException> lines(boolean chopped)
            throws IOException;

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

}

package net.bodz.bas.io.preparation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IterableX;
import net.bodz.bas.collection.iterator.IteratorTargetException;

public interface IStreamReadPreparation {

    int getBlockSize();

    void setBlockSize(int blockSize);

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    byte[] readBytes()
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    byte[] readBytes(int maxBytesToRead)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    String readString()
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    String readString(int maxBytesToRead)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    List<byte[]> listBlocks(int maxBlocks)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    ImmediateIteratorX<byte[], ? extends IOException> blockIterator()
            throws IOException;

    /**
     * @return {@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<byte[], IOException> blocks()
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

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    ImmediateIteratorX<String, ? extends IOException> lineIterator(boolean chopped)
            throws IOException;

    /**
     * @return {@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<String, IOException> lines(boolean chopped)
            throws IOException;

}

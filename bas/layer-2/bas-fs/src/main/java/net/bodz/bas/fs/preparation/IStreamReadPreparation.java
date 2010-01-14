package net.bodz.bas.fs.preparation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IterableX;
import net.bodz.bas.collection.iterator.IteratorTargetException;

public interface IStreamReadPreparation {

    int getBlockSize();

    void setBlockSize(int blockSize);

    Charset getCharset();

    /**
     * @return non-<code>null</code> value.
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     *             If can't open {@link InputStream}.
     */
    InputStream newInputStream()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     *             If can't open {@link Reader}.
     */
    Reader newReader()
            throws IOException;

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
    ImmediateIteratorX<byte[], IOException> blockIterator()
            throws IOException;

    /**
     * @return {@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<byte[], ? extends IOException> blocks()
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
    ImmediateIteratorX<String, IOException> lineIterator(boolean chopped)
            throws IOException;

    /**
     * @return {@link Iterable} which may throw {@link IteratorTargetException}.
     */
    IterableX<String, ? extends IOException> lines(boolean chopped)
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    ZipFile newZipFile()
            throws IOException;

    /**
     * @throws FileNotFoundException
     *             If file doesn't exist.
     * @throws IOException
     */
    JarFile newJarFile(boolean verify)
            throws IOException;

}

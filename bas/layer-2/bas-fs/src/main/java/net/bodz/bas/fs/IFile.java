package net.bodz.bas.fs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.collection.iterator.ImmediateIterator;

public interface IFile
        extends IEntry, Cloneable {

    /**
     * @return <code>null</code> if {@link Charset} is unknown.
     */
    Charset getCharset();

    /**
     * Override default charset.
     * 
     * @see #newReader(Charset)
     * @see #newWriter(Charset, boolean)
     */
    void setCharset(Charset charset);

    /**
     * @return <code>null</code> if MIME-Type is unknown.
     */
    String getMIMEType();

    /**
     * @return <code>null</code> is length is unknown.
     */
    Long getLength();

    /**
     * @throws IOException
     *             if the file is existed and can't be deleted.
     * @return <code>false</code> if the file wasn't existed.
     */
    boolean delete()
            throws IOException;

    boolean getAppendMode();

    void setAppendMode(boolean appendMode);

    boolean isAutoFlush();

    void setAutoFlush(boolean autoFlush);

    boolean isDeleteOnExit();

    void setDeleteOnExit(boolean deleteOnExit);

    InputStream newInputStream()
            throws IOException;

    OutputStream newOutputStream()
            throws IOException;

    PrintStream newPrintStream()
            throws IOException;

    Reader newReader()
            throws IOException;

    Writer newWriter()
            throws IOException;

    RandomAccessFile newRandomAccessFile(String mode)
            throws IOException;

    ZipFile newZipFile()
            throws IOException;

    JarFile newJarFile(boolean verify)
            throws IOException;

    int getBlockSize();

    void setBlockSize(int blockSize);

    byte[] readBytes(int maxBytesToRead)
            throws IOException;

    void writeBytes(byte[] bytes)
            throws IOException;

    /**
     * If <code>off</code> is negative, or <code>len</code> is negative, or <code>off+len</code> is
     * greater than the length of the array <code>bytes</code>, then an
     * {@link IndexOutOfBoundsException} is thrown.
     */
    void writeBytes(byte[] bytes, int off, int len)
            throws IOException;

    String readString(int maxBytesToRead)
            throws IOException;

    void writeString(String string)
            throws IOException;

    ImmediateIterator<String, IOException> lineIterator(boolean chopped)
            throws IOException;

    ImmediateIterator<byte[], IOException> blockIterator()
            throws IOException;

    List<String> getTextContents(boolean chopped)
            throws IOException;

}

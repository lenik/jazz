package net.bodz.bas.io.resource.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.security.Cryptos;
import net.bodz.bas.err.RuntimeIOException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamInputSourceWrapper;
import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitors;
import net.bodz.bas.t.iterator.immed.Mitorx;
import net.bodz.bas.t.iterator.immed.OverlappedMitor;

public class StreamReading
        implements IStreamReading {

    private final IStreamInputSource source;
    private int blockSize = 4096;

    public StreamReading(IStreamInputSourceWrapper wrapper) {
        this(wrapper.getInputSource());
    }

    public StreamReading(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

    @Override
    public IStreamReading clone() {
        StreamReading clone;
        try {
            clone = (StreamReading) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return clone;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public StreamReading setBlockSize(int blockSize) {
        if (blockSize <= 0)
            throw new IllegalArgumentException("blockSize must be positive: " + blockSize);
        this.blockSize = blockSize;
        return this;
    }

    @Override
    public byte[] readBinaryContents()
            throws IOException, OutOfMemoryError {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        IByteIn in = source.newByteIn();
        try {
            while (true) {
                int readSize = block.length;
                int n = in.read(block, 0, readSize);
                if (n == -1)
                    break;
                buffer.write(block, 0, n);
            }
        } finally {
            in.close();
        }
        return buffer.toByteArray();
    }

    /**
     * @seecopy {@link #readBinaryContents()}
     */
    @GeneratedByCopyPaste
    @Override
    public String readTextContents()
            throws IOException, OutOfMemoryError {
        CharArrayWriter buffer = new CharArrayWriter();
        char block[] = new char[blockSize];
        ICharIn in = source.newCharIn();
        try {
            while (true) {
                int readSize = block.length;
                int n = in.read(block, 0, readSize);
                if (n == -1)
                    break;
                buffer.write(block, 0, n);
            }
        } finally {
            in.close();
        }
        return buffer.toString();
    }

    public byte[] readBytes(int maxBytesToRead)
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        IByteIn in = source.newByteIn();
        int remaining = maxBytesToRead;
        try {
            while (remaining != 0) {
                int readSize = block.length;
                if (remaining != -1 && readSize > remaining)
                    readSize = remaining;
                int n = in.read(block, 0, readSize);
                if (n == -1)
                    break;
                if (remaining != -1)
                    remaining -= n;
                buffer.write(block, 0, n);
            }
        } finally {
            in.close();
        }
        return buffer.toByteArray();
    }

    /**
     * @seecopy {@link #readBytes(int)}
     */
    @GeneratedByCopyPaste
    public char[] readChars(int maxCharsToRead)
            throws IOException {
        CharArrayWriter buffer = new CharArrayWriter();
        char block[] = new char[blockSize];
        ICharIn in = source.newCharIn();
        int remaining = maxCharsToRead;
        try {
            while (remaining != 0) {
                int readSize = block.length;
                if (remaining != -1 && readSize > remaining)
                    readSize = remaining;
                int n = in.read(block, 0, readSize);
                if (n == -1)
                    break;
                if (remaining != -1)
                    remaining -= n;
                buffer.write(block, 0, n);
            }
        } finally {
            in.close();
        }
        return buffer.toCharArray();
    }

    public String readTill(char term)
            throws IOException {
        StringBuilder buffer = new StringBuilder();
        ICharIn in = source.newCharIn();
        int c;
        try {
            while ((c = in.read()) >= 0) {
                if (c == term)
                    break;
                buffer.append((char) c);
            }
        } finally {
            in.close();
        }
        return buffer.toString();
    }

    public Mitorx<byte[], IOException> byteBlocks(final boolean allowOverlap)
            throws IOException {
        final byte[] block = new byte[blockSize];
        final IByteIn in = source.newByteIn();
        return new OverlappedMitor<byte[], IOException>() {

            @Override
            public byte[] deoverlap(byte[] o) {
                return Arrays.copyOf(o, o.length);
            }

            public byte[] _next()
                    throws IOException {
                int off = 0;
                int blockRemain = blockSize;
                int zeroRetry = 0;
                while (blockRemain != 0) {
                    int cb = in.read(block, off, blockRemain);
                    if (cb == -1)
                        break;
                    if (cb == 0) {
                        if (++zeroRetry > 3)
                            throw new IOException("Repeat read zero-length block");
                    } else {
                        off += cb;
                        blockRemain -= cb;
                        zeroRetry = 0;
                    }
                }
                if (blockRemain == 0)
                    return allowOverlap ? Arrays.copyOf(block, block.length) : block;

                if (off == 0) {
                    in.close();
                    return end();
                }

                byte[] partialBlock = new byte[off];
                System.arraycopy(block, 0, partialBlock, 0, off);
                return partialBlock;
            }

        };
    }

    @Override
    public Iterable<byte[]> byteBlocks()
            throws IOException {
        return new Iterable<byte[]>() {

            @Override
            public Iterator<byte[]> iterator() {
                Mitorx<byte[], IOException> mitor;
                try {
                    mitor = byteBlocks(true);
                } catch (IOException e) {
                    throw new RuntimeIOException(e);
                }
                return Mitors.convert(mitor);
            }

        };
    }

    public List<byte[]> listByteBlocks(int maxBlocks)
            throws IOException {
        return Mitors.toListLimited(byteBlocks(false), maxBlocks);
    }

    /**
     * @seecopy {@link #byteBlocks(boolean)}
     */
    @GeneratedByCopyPaste
    public Mitorx<char[], IOException> charBlocks(final boolean allowOverlap)
            throws IOException {
        final char[] block = new char[blockSize];
        final ICharIn in = source.newCharIn();
        return new OverlappedMitor<char[], IOException>() {

            @Override
            public char[] deoverlap(char[] o) {
                return Arrays.copyOf(o, o.length);
            }

            public char[] _next()
                    throws IOException {
                int off = 0;
                int blockRemain = blockSize;
                int zeroRetry = 0;
                while (blockRemain != 0) {
                    int cb = in.read(block, off, blockRemain);
                    if (cb == -1)
                        break;
                    if (cb == 0) {
                        if (++zeroRetry > 3)
                            throw new IOException("Repeat read zero-length block");
                    } else {
                        off += cb;
                        blockRemain -= cb;
                        zeroRetry = 0;
                    }
                }
                if (blockRemain == 0)
                    return allowOverlap ? Arrays.copyOf(block, block.length) : block;

                if (off == 0) {
                    in.close();
                    return end();
                }

                char[] partialBlock = new char[off];
                System.arraycopy(block, 0, partialBlock, 0, off);
                return partialBlock;
            }

        };
    }

    /**
     * @seecopy {@link #byteBlocks()}
     */
    @GeneratedByCopyPaste
    @Override
    public Iterable<char[]> charBlocks() {
        return new Iterable<char[]>() {

            @Override
            public Iterator<char[]> iterator() {
                Mitorx<char[], IOException> immIter;
                try {
                    immIter = charBlocks(true);
                } catch (IOException e) {
                    throw new RuntimeIOException(e);
                }
                return Mitors.convert(immIter);
            }

        };
    }

    /**
     * @seecopy {@link #listByteBlocks(int)}
     */
    @GeneratedByCopyPaste
    public List<char[]> listCharBlocks(int maxBlocks)
            throws IOException {
        return Mitors.toListLimited(charBlocks(false), maxBlocks);
    }

    @Override
    public Mitorx<String, ? extends IOException> _lines(boolean chopped)
            throws IOException {
        if (chopped) {
            final BufferedReader bufferedReader = source.newBufferedReader();
            return new AbstractMitorx<String, IOException>() {

                public String _next()
                        throws IOException {
                    String line = bufferedReader.readLine();
                    if (line == null)
                        end();
                    return line;
                }

            };
        } else {
            final LineReader lineReader = source.newLineReader();
            return new AbstractMitorx<String, IOException>() {

                public String _next()
                        throws IOException {
                    String line = lineReader.readLine();
                    if (line == null)
                        end();
                    return line;
                }

            };
        }
    }

    @Override
    public Iterable<String> lines() {
        return lines(false);
    }

    @Override
    public Iterable<String> lines(final boolean chopped) {
        return new Iterable<String>() {

            @Override
            public Iterator<String> iterator() {
                Mitorx<String, ? extends IOException> mitor;
                try {
                    mitor = _lines(chopped);
                } catch (IOException e) {
                    throw new RuntimeIOException(e);
                }
                return Mitors.convert(mitor);
            }

        };
    }

    @Override
    public List<String> listLines()
            throws IOException {
        return Mitors.toList(_lines(false));
    }

    @Override
    public List<String> listLines(boolean chopped, int maxLines)
            throws IOException {
        return Mitors.toListLimited(_lines(chopped), maxLines);
    }

    public byte[] digest(MessageDigest digest)
            throws IOException {
        Mitorx<byte[], ? extends IOException> blocks = byteBlocks(true);
        byte[] block;
        try {
            while ((block = blocks._next()) != null || !blocks.isEnded())
                digest.update(block);
        } catch (RuntimeIOException e) {
            e.rethrow(IOException.class);
        }
        return digest.digest();
    }

    public byte[] md5()
            throws IOException {
        MessageDigest md5 = Cryptos.getMD5();
        return digest(md5);
    }

    public byte[] sha1()
            throws IOException {
        MessageDigest sha1 = Cryptos.getSHA1();
        return digest(sha1);
    }

}

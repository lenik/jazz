package net.bodz.bas.io.resource.preparation;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.collection.iterator.AbstractIterableX;
import net.bodz.bas.collection.iterator.AbstractIteratorMX;
import net.bodz.bas.collection.iterator.IterableX;
import net.bodz.bas.collection.iterator.IteratorM2X;
import net.bodz.bas.collection.iterator.IteratorMX;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.collection.iterator.OverlappedIteratorMX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.meta.codereview.GeneratedByCopyPaste;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.ICharIn;

public class StreamReadPreparation
        implements IStreamReadPreparation {

    private final IStreamInputSource source;
    private int blockSize = 4096;

    public StreamReadPreparation(IStreamInputSource source) {
        if (source == null)
            throw new NullPointerException("source");
        this.source = source;
    }

    @Override
    public IStreamReadPreparation clone() {
        StreamReadPreparation clone;
        try {
            clone = (StreamReadPreparation) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return clone;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public StreamReadPreparation setBlockSize(int blockSize) {
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

    public IteratorMX<byte[], IOException> byteBlocks(final boolean allowOverlap)
            throws IOException {
        final byte[] block = new byte[blockSize];
        final IByteIn in = source.newByteIn();
        return new OverlappedIteratorMX<byte[], IOException>() {

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
    public IterableX<byte[], IOException> byteBlocks()
            throws IOException {
        return new AbstractIterableX<byte[], IOException>() {

            @Override
            public IteratorX<byte[], IOException> iteratorX() {
                IteratorMX<byte[], IOException> immIter;
                try {
                    immIter = byteBlocks(true);
                } catch (IOException e) {
                    throw new IteratorTargetException(e);
                }
                return new IteratorM2X<byte[], IOException>(immIter);
            }

        };
    }

    public List<byte[]> listByteBlocks(int maxBlocks)
            throws IOException {
        return IteratorToList.toListLimited(byteBlocks(false), maxBlocks);
    }

    /**
     * @seecopy {@link #byteBlocks(boolean)}
     */
    @GeneratedByCopyPaste
    public IteratorMX<char[], IOException> charBlocks(final boolean allowOverlap)
            throws IOException {
        final char[] block = new char[blockSize];
        final ICharIn in = source.newCharIn();
        return new OverlappedIteratorMX<char[], IOException>() {

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
    public IterableX<char[], IOException> charBlocks()
            throws IOException {
        return new AbstractIterableX<char[], IOException>() {

            @Override
            public IteratorX<char[], IOException> iteratorX() {
                IteratorMX<char[], IOException> immIter;
                try {
                    immIter = charBlocks(true);
                } catch (IOException e) {
                    throw new IteratorTargetException(e);
                }
                return new IteratorM2X<char[], IOException>(immIter);
            }

        };
    }

    /**
     * @seecopy {@link #listByteBlocks(int)}
     */
    @GeneratedByCopyPaste
    public List<char[]> listCharBlocks(int maxBlocks)
            throws IOException {
        return IteratorToList.toListLimited(charBlocks(false), maxBlocks);
    }

    @Override
    public IteratorMX<String, ? extends IOException> lines(boolean allowOverlap, boolean chopped)
            throws IOException {
        if (chopped) {
            final BufferedReader bufferedReader = source.newBufferedReader();
            return new AbstractIteratorMX<String, IOException>() {

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
            return new AbstractIteratorMX<String, IOException>() {

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
    public IterableX<String, IOException> lines(final boolean chopped)
            throws IOException {
        return new AbstractIterableX<String, IOException>() {

            @Override
            public IteratorX<String, IOException> iteratorX() {
                IteratorMX<String, ? extends IOException> immIter;
                try {
                    immIter = lines(true, chopped);
                } catch (IOException e) {
                    throw new IteratorTargetException(e);
                }
                return new IteratorM2X<String, IOException>(immIter);
            }

        };
    }

    @Override
    public List<String> listLines()
            throws IOException {
        return IteratorToList.toList(lines(false, false));
    }

    @Override
    public List<String> listLines(boolean chopped, int maxLines)
            throws IOException {
        return IteratorToList.toListLimited(lines(false, chopped), maxLines);
    }

}

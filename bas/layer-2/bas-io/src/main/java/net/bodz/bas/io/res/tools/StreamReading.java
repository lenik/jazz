package net.bodz.bas.io.res.tools;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.c.java.io.LineReader;
import net.bodz.bas.c.java.security.Cryptos;
import net.bodz.bas.err.RuntimeIOException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamInputSourceWrapper;
import net.bodz.bas.meta.codegen.CopyAndPaste;
import net.bodz.bas.t.iterator.immed.AbstractMitorx;
import net.bodz.bas.t.iterator.immed.Mitors;
import net.bodz.bas.t.iterator.immed.Mitorx;
import net.bodz.bas.t.iterator.immed.OverlappedMitor;

public class StreamReading
        implements
            IStreamReading {

    private final IStreamInputSource source;
    private OpenOption[] openOptions = {};
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
        StreamReading o = new StreamReading(source);
        o.blockSize = blockSize;
        o.openOptions = openOptions;
        return o;
    }

    @Override
    public OpenOption[] getOpenOptions() {
        return openOptions;
    }

    @Override
    public IStreamReading setOpenOptions(OpenOption... options) {
        this.openOptions = options;
        return this;
    }

    @Override
    public int blockSize() {
        return blockSize;
    }

    @Override
    public StreamReading blockSize(int blockSize) {
        if (blockSize <= 0)
            throw new IllegalArgumentException("blockSize must be positive: " + blockSize);
        this.blockSize = blockSize;
        return this;
    }

    @Override
    public byte[] read()
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        IByteIn in = source.newByteIn(openOptions);
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

    @Override
    public byte[] read(int maxBytesToRead)
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        IByteIn in = source.newByteIn(openOptions);
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

    @Override
    public char[] readChars()
            throws IOException {
        if (source.isTextMode())
            return readCharsByChars();
        else
            return readCharsByBytes();
    }

    public char[] readCharsByBytes()
            throws IOException {
        return readStringByBytes().toCharArray();
    }

    public char[] readCharsByChars()
            throws IOException {
        return readStringByChars().toCharArray();
    }

    /**
     * @seecopy {@link #read(int)}
     */
    @CopyAndPaste
    @Override
    public char[] readChars(int maxCharsToRead)
            throws IOException {
        CharArrayWriter buffer = new CharArrayWriter();
        char block[] = new char[blockSize];
        ICharIn in = source.newCharIn(openOptions);
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

    /**
     * @seecopy {@link #read()}
     */
    @CopyAndPaste
    @Override
    public String readString()
            throws IOException {
        if (source.isTextMode())
            return readStringByChars();
        else
            return readStringByBytes();
    }

    public String readStringByBytes()
            throws IOException {
        byte[] buf = read();
        Charset charset = source.getCharset();
        String str = new String(buf, charset);
        return str;
    }

    public String readStringByChars()
            throws IOException {
        CharArrayWriter buffer = new CharArrayWriter();
        char block[] = new char[blockSize];
        ICharIn in = source.newCharIn(openOptions);
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

    public String readStringTill(char term)
            throws IOException {
        StringBuilder buffer = new StringBuilder();
        ICharIn in = source.newCharIn(openOptions);
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

    @Override
    public Mitorx<byte[], IOException> blocks(final boolean allowOverlap)
            throws IOException {
        final byte[] block = new byte[blockSize];
        final IByteIn in = source.newByteIn(openOptions);
        return new OverlappedMitor<byte[], IOException>() {

            @Override
            public byte[] deoverlap(byte[] o) {
                return Arrays.copyOf(o, o.length);
            }

            @Override
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
    public Iterable<byte[]> blocks()
            throws IOException {
        return new Iterable<byte[]>() {

            @Override
            public Iterator<byte[]> iterator() {
                Mitorx<byte[], IOException> mitor;
                try {
                    mitor = blocks(true);
                } catch (IOException e) {
                    throw new RuntimeIOException(e);
                }
                return Mitors.convert(mitor);
            }

        };
    }

    @Override
    public List<byte[]> readBlocks(int maxBlocks)
            throws IOException {
        return Mitors.toListLimited(blocks(false), maxBlocks);
    }

    /**
     * @seecopy {@link #blocks(boolean)}
     */
    @Override
    @CopyAndPaste
    public Mitorx<char[], IOException> charBlocks(final boolean allowOverlap)
            throws IOException {
        final char[] block = new char[blockSize];
        final ICharIn in = source.newCharIn(openOptions);
        return new OverlappedMitor<char[], IOException>() {

            @Override
            public char[] deoverlap(char[] o) {
                return Arrays.copyOf(o, o.length);
            }

            @Override
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
     * @seecopy {@link #blocks()}
     */
    @CopyAndPaste
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
     * @seecopy {@link #readBlocks(int)}
     */
    @CopyAndPaste
    @Override
    public List<char[]> readCharBlocks(int maxBlocks)
            throws IOException {
        return Mitors.toListLimited(charBlocks(false), maxBlocks);
    }

    @Override
    public Mitorx<String, ? extends IOException> _lines(boolean chopped)
            throws IOException {
        if (chopped) {
            final BufferedReader bufferedReader = source.newBufferedReader(openOptions);
            return new AbstractMitorx<String, IOException>() {

                @Override
                public String _next()
                        throws IOException {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        bufferedReader.close();
                        return end();
                    }
                    return line;
                }

            };
        } else {
            final LineReader lineReader = source.newLineReader(openOptions);
            return new AbstractMitorx<String, IOException>() {

                @Override
                public String _next()
                        throws IOException {
                    String line = lineReader.readLine();
                    if (line == null) {
                        lineReader.close();
                        return end();
                    }
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
    public List<String> readLines()
            throws IOException {
        return Mitors.toList(_lines(false));
    }

    @Override
    public List<String> readLines(boolean chopped)
            throws IOException {
        return Mitors.toList(_lines(chopped));
    }

    @Override
    public List<String> readLines(boolean chopped, int maxLines)
            throws IOException {
        return Mitors.toListLimited(_lines(chopped), maxLines);
    }

    @Override
    public byte[] digest(MessageDigest digest)
            throws IOException {
        Mitorx<byte[], ? extends IOException> blocks = blocks(true);
        byte[] block;
        try {
            while ((block = blocks._next()) != null || ! blocks.isEnded())
                digest.update(block);
        } catch (RuntimeIOException e) {
            e.rethrow(IOException.class);
        }
        return digest.digest();
    }

    @Override
    public byte[] md5()
            throws IOException {
        MessageDigest md5 = Cryptos.getMD5();
        return digest(md5);
    }

    @Override
    public byte[] sha1()
            throws IOException {
        MessageDigest sha1 = Cryptos.getSHA1();
        return digest(sha1);
    }

}

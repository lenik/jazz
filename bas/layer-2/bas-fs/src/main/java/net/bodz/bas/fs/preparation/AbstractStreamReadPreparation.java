package net.bodz.bas.fs.preparation;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.collection.iterator.AbstractImmediateIteratorX;
import net.bodz.bas.collection.iterator.ImmIterIterator;
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.IterableX;
import net.bodz.bas.collection.iterator.IteratorTargetException;
import net.bodz.bas.collection.iterator.IteratorX;
import net.bodz.bas.collection.iterator.OverlappedImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.fs.IFile;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7ZipFile;

public abstract class AbstractStreamReadPreparation
        implements IStreamReadPreparation {

    private final IFile file;
    private int blockSize = 4096;

    public AbstractStreamReadPreparation(IFile file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        if (blockSize <= 0)
            throw new IllegalArgumentException("blockSize must be positive: " + blockSize);
        this.blockSize = blockSize;
    }

    public Charset getCharset() {
        return file.getCharset();
    }

    public abstract InputStream newInputStream()
            throws FileNotFoundException, IOException;

    public Reader newReader()
            throws FileNotFoundException, IOException {
        InputStream in = newInputStream();
        return new InputStreamReader(in, getCharset());
    }

    @Override
    public byte[] readBytes()
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        InputStream in = newInputStream();
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

    public byte[] readBytes(int maxBytesToRead)
            throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte block[] = new byte[blockSize];
        InputStream in = newInputStream();
        int remaining = maxBytesToRead;
        try {
            while (remaining != 0) {
                int readSize = block.length;
                if (remaining != -1 && readSize > remaining)
                    readSize = (int) remaining;
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
    public String readString()
            throws IOException {
        byte[] bytes = readBytes();
        return new String(bytes, getCharset());
    }

    public String readString(int maxBytesToRead)
            throws IOException {
        byte[] bytes = readBytes(maxBytesToRead);
        return new String(bytes, getCharset());
    }

    public String readTill(char term)
            throws IOException {
        Reader reader = newReader();
        StringBuffer buffer = new StringBuffer();
        int c;
        try {
            while ((c = reader.read()) >= 0) {
                if (c == term)
                    break;
                buffer.append((char) c);
            }
        } finally {
            reader.close();
        }
        return buffer.toString();
    }

    public List<byte[]> listBlocks(int maxBlocks)
            throws IOException {
        return IteratorToList.toListLimited(blockIterator(), maxBlocks);
    }

    public ImmediateIteratorX<byte[], IOException> blockIterator()
            throws IOException {
        final InputStream in = newInputStream();
        final byte[] block = new byte[blockSize];

        return new OverlappedImmediateIteratorX<byte[], IOException>() {

            @Override
            public byte[] deoverlap(byte[] o) {
                return Arrays.copyOf(o, o.length);
            }

            public byte[] next()
                    throws IOException {
                int off = 0;
                int remaining = blockSize;
                int zeroRetry = 0;
                while (remaining != 0) {
                    int cb = in.read(block, off, remaining);
                    if (cb == -1)
                        break;
                    if (cb == 0) {
                        if (++zeroRetry > 3)
                            throw new IOException("Repeat read zero-length block");
                    } else {
                        off += cb;
                        remaining -= cb;
                        zeroRetry = 0;
                    }
                }
                if (remaining == 0)
                    return block;
                byte[] partialBlock = new byte[off];
                System.arraycopy(block, 0, partialBlock, 0, off);
                return partialBlock;
            }

        };
    }

    public List<String> listLines()
            throws IOException {
        return IteratorToList.toList(lineIterator(false));
    }

    public List<String> listLines(boolean chopped, int maxLines)
            throws IOException {
        return IteratorToList.toListLimited(lineIterator(chopped), maxLines);
    }

    public ImmediateIteratorX<String, IOException> lineIterator(boolean chopped)
            throws IOException {
        Reader r = newReader();
        if (chopped) {
            final BufferedReader reader = new BufferedReader(r);
            return new AbstractImmediateIteratorX<String, IOException>() {

                public String next()
                        throws IOException {
                    String line = reader.readLine();
                    if (line == null)
                        end();
                    return line;
                }

            };
        } else {
            final LineReader lineReader = new LineReader(r);
            return new AbstractImmediateIteratorX<String, IOException>() {

                public String next()
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
    public IterableX<byte[], ? extends IOException> blocks()
            throws IOException {
        return new IterableX<byte[], IOException>() {

            @Override
            public IteratorX<byte[], IOException> iterator() {
                ImmediateIteratorX<byte[], IOException> immIter;
                try {
                    immIter = blockIterator();
                } catch (IOException e) {
                    throw new IteratorTargetException(e);
                }
                return new ImmIterIterator<byte[], IOException>(immIter);
            }

        };
    }

    @Override
    public IterableX<String, ? extends IOException> lines(final boolean chopped)
            throws IOException {
        return new IterableX<String, IOException>() {

            @Override
            public IteratorX<String, IOException> iterator() {
                ImmediateIteratorX<String, IOException> immIter;
                try {
                    immIter = lineIterator(chopped);
                } catch (IOException e) {
                    throw new IteratorTargetException(e);
                }
                return new ImmIterIterator<String, IOException>(immIter);
            }

        };
    }

    public ZipFile newZipFile()
            throws IOException {
        File f = file.getFile();
        if (f == null)
            throw new UnsupportedOperationException("Not a (java.io.)file");
        int mode = ZipFile.OPEN_READ;
        if (file.isDeleteOnExit())
            mode |= ZipFile.OPEN_DELETE;
        return Jdk7ZipFile.newInstance(f, mode, getCharset());
    }

    public JarFile newJarFile(boolean verify)
            throws IOException {
        File f = this.file.getFile();
        if (f == null)
            throw new UnsupportedOperationException("Not a (java.io.)file");
        if (!Charset.forName("UTF-8").equals(getCharset()))
            throw new UnsupportedOperationException("Jar is encoded by UTF-8 only");
        int mode = JarFile.OPEN_READ;
        if (this.file.isDeleteOnExit())
            mode |= JarFile.OPEN_DELETE;
        return new JarFile(f, verify, mode);
    }

}

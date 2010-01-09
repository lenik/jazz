package net.bodz.bas.fs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import net.bodz.bas.collection.iterator.ImmediateIteratorX;
import net.bodz.bas.collection.iterator.OverlappedImmediateIteratorX;
import net.bodz.bas.collection.util.IteratorToList;
import net.bodz.bas.io.LineReader;

public abstract class AbstractReadToolkit {

    private final IFile file;
    private int blockSize = 4096;

    public AbstractReadToolkit(IFile file) {
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
            throws IOException;

    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        return new InputStreamReader(in, getCharset());
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

    public String readString(int maxBytesToRead)
            throws IOException {
        byte[] bytes = readBytes(maxBytesToRead);
        return new String(bytes, getCharset());
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

    public ZipFile newZipFile()
            throws IOException {
        File f = file.getFile();
        if (f == null)
            throw new UnsupportedOperationException("Not a (java.io.)file");
        int mode = ZipFile.OPEN_READ;
        if (file.isDeleteOnExit())
            mode |= ZipFile.OPEN_DELETE;
        return new ZipFile(f, mode, getCharset());
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

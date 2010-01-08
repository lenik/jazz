package net.bodz.bas.fs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import net.bodz.bas.collection.iterator.AbstractImmediateIterator;
import net.bodz.bas.collection.iterator.ImmediateIterator;
import net.bodz.bas.collection.iterator.OverlappedImmediateIterator;
import net.bodz.bas.io.LineReader;

public abstract class AbstractFile
        extends AbstractEntry
        implements IFile {

    private boolean appendMode;
    private boolean autoFlush;
    private boolean deleteOnExit;
    private int blockSize = 4096;
    private Charset charset = Charset.defaultCharset();

    public AbstractFile(IFolder parentFolder, String name) {
        super(parentFolder, name);
    }

    @Override
    public boolean getAppendMode() {
        return appendMode;
    }

    @Override
    public void setAppendMode(boolean appendMode) {
        this.appendMode = appendMode;
    }

    @Override
    public int getBlockSize() {
        return blockSize;
    }

    @Override
    public void setBlockSize(int blockSize) {
        if (blockSize <= 0)
            throw new IllegalArgumentException("blockSize must be positive: " + blockSize);
        this.blockSize = blockSize;
    }

    @Override
    public boolean isAutoFlush() {
        return autoFlush;
    }

    @Override
    public void setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
    }

    @Override
    public boolean isDeleteOnExit() {
        return deleteOnExit;
    }

    @Override
    public void setDeleteOnExit(boolean deleteOnExit) {
        this.deleteOnExit = deleteOnExit;
    }

    @Override
    public Charset getCharset() {
        return charset;
    }

    @Override
    public void setCharset(Charset charset) {
        if (charset == null)
            throw new NullPointerException("charset");
        this.charset = charset;
    }

    @Override
    public String getMIMEType() {
        String extension = getExtension(false, 1);
        return MIMETypes.guessByExtension(extension);
    }

    @Override
    public PrintStream newPrintStream()
            throws IOException {
        OutputStream out = newOutputStream();
        return new PrintStream(out, autoFlush, charset.name());
    }

    @Override
    public Reader newReader()
            throws IOException {
        InputStream in = newInputStream();
        return new InputStreamReader(in, charset);
    }

    @Override
    public Writer newWriter()
            throws IOException {
        OutputStream out = newOutputStream();
        return new OutputStreamWriter(out, charset);
    }

    @Override
    public JarFile newJarFile(boolean verify)
            throws IOException {
        File file = getFile();
        if (file == null)
            throw new UnsupportedOperationException("Not a (java.io.)file");
        if (!Charset.forName("UTF-8").equals(charset))
            throw new UnsupportedOperationException("Jar is encoded by UTF-8 only");
        int mode = JarFile.OPEN_READ;
        if (isDeleteOnExit())
            mode |= JarFile.OPEN_DELETE;
        return new JarFile(file, verify, mode);
    }

    @Override
    public ZipFile newZipFile()
            throws IOException {
        File file = getFile();
        if (file == null)
            throw new UnsupportedOperationException("Not a (java.io.)file");
        int mode = ZipFile.OPEN_READ;
        if (isDeleteOnExit())
            mode |= ZipFile.OPEN_DELETE;
        return new ZipFile(file, mode, charset);
    }

    @Override
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
    public String readString(int maxBytesToRead)
            throws IOException {
        byte[] bytes = readBytes(maxBytesToRead);
        return new String(bytes, charset);
    }

    @Override
    public void writeBytes(byte[] bytes, int off, int len)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        OutputStream out = newOutputStream();
        try {
            out.write(bytes, off, len);
        } finally {
            out.close();
        }
    }

    @Override
    public void writeBytes(byte[] bytes)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        writeBytes(bytes, 0, bytes.length);
    }

    @Override
    public void writeString(String string)
            throws IOException {
        byte[] bytes = string.getBytes(charset);
        writeBytes(bytes);
    }

    @Override
    public ImmediateIterator<byte[], IOException> blockIterator()
            throws IOException {
        final InputStream in = newInputStream();
        final byte[] block = new byte[blockSize];

        return new OverlappedImmediateIterator<byte[], IOException>() {

            @Override
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

    @Override
    public ImmediateIterator<String, IOException> lineIterator(boolean chopped)
            throws IOException {
        Reader r = newReader();
        if (chopped) {
            final BufferedReader reader = new BufferedReader(r);
            return new AbstractImmediateIterator<String, IOException>() {

                @Override
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
            return new AbstractImmediateIterator<String, IOException>() {

                @Override
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
    public List<String> getTextContents(boolean chopped)
            throws IOException {
        List<String> lines = new ArrayList<String>();
        ImmediateIterator<String, IOException> iterator = lineIterator(chopped);
        String line;
        while ((line = iterator.next()) != null) {
            lines.add(line);
        }
        return lines;
    }

    protected <T extends AbstractFile> T clone(T o) {
        o.appendMode = appendMode;
        o.autoFlush = autoFlush;
        o.blockSize = blockSize;
        o.charset = charset;
        o.deleteOnExit = deleteOnExit;
        return super.clone(o);
    }

}

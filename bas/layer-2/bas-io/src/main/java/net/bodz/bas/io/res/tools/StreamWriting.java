package net.bodz.bas.io.res.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.io.res.IStreamOutputTarget;
import net.bodz.bas.io.res.IStreamOutputTargetWrapper;
import net.bodz.bas.io.res.builtin.InputStreamSource;
import net.bodz.bas.io.res.builtin.ReaderSource;
import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;

public class StreamWriting
        implements IStreamWriting {

    private final IStreamOutputTarget target;

    private OpenOption[] openOptions = {};

    private boolean autoFlush;
    private int blockSize = 4096;

    public StreamWriting(IStreamOutputTargetWrapper wrapper) {
        this(wrapper.getOutputTarget());
    }

    public StreamWriting(IStreamOutputTarget target) {
        if (target == null)
            throw new NullPointerException("target");
        this.target = target;
    }

    @Override
    public IStreamWriting clone() {
        StreamWriting o = new StreamWriting(target);
        o.openOptions = openOptions;
        o.autoFlush = autoFlush;
        o.blockSize = blockSize;
        return o;
    }

    @Override
    public OpenOption[] getOpenOptions() {
        return openOptions;
    }

    @Override
    public StreamWriting setOpenOptions(OpenOption... openOptions) {
        this.openOptions = openOptions;
        return this;
    }

    @Override
    public boolean isAutoFlush() {
        return autoFlush;
    }

    @Override
    public StreamWriting setAutoFlush(boolean autoFlush) {
        this.autoFlush = autoFlush;
        return this;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public StreamWriting setBlockSize(int blockSize) {
        if (blockSize <= 0)
            throw new IllegalArgumentException("blockSize must be positive: " + blockSize);
        this.blockSize = blockSize;
        return this;
    }

    @Override
    public void write(byte[] bytes, int off, int len)
            throws IOException {
        if (bytes == null)
            throw new NullPointerException("bytes");
        IByteOut out = target.newByteOut(openOptions);
        try {
            out.write(bytes, off, len);
        } finally {
            out.close();
        }
    }

    @Override
    public void write(byte[] bytes)
            throws IOException {
        write(bytes, 0, bytes.length);
    }

    @Override
    public void writeChars(char[] chars, int off, int len)
            throws IOException {
        if (chars == null)
            throw new NullPointerException("chars");
        ICharOut out = target.newCharOut(openOptions);
        try {
            out.write(chars, off, len);
        } finally {
            out.close();
        }
    }

    @Override
    public void writeChars(char[] chars)
            throws IOException {
        writeChars(chars, 0, chars.length);
    }

    @Override
    public void write(IStreamInputSource source, int maxLength)
            throws IOException {
        if (source == null)
            throw new NullPointerException("source");
        if (maxLength < 0)
            throw new IllegalArgumentException("maxLength is negative: " + maxLength);
        byte[] block = new byte[blockSize];
        IByteIn in = source.newByteIn();
        try {
            IByteOut out = target.newByteOut(openOptions);
            try {
                int limit = maxLength;
                while (limit > 0) {
                    int actual = in.read(block, 0, Math.min(limit, block.length));
                    if (actual == -1)
                        break;
                    limit -= actual;
                    out.write(block, 0, actual);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    @Override
    public void write(IStreamInputSource source)
            throws IOException {
        if (source == null)
            throw new NullPointerException("source");
        byte[] block = new byte[blockSize];
        IByteIn in = source.newByteIn();
        try {
            IByteOut out = target.newByteOut(openOptions);
            try {
                while (true) {
                    int actual = in.read(block, 0, block.length);
                    if (actual == -1)
                        break;
                    out.write(block, 0, actual);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    @Override
    public void write(InputStream in)
            throws IOException {
        write(new InputStreamSource(in));
    }

    /**
     * @seecopy {@link #write(IStreamInputSource, int)}
     */
    @GeneratedByCopyPaste
    @Override
    public void writeChars(IStreamInputSource source, int maxLength)
            throws IOException {
        if (source == null)
            throw new NullPointerException("source");
        if (maxLength < 0)
            throw new IllegalArgumentException("maxLength is negative: " + maxLength);
        char[] block = new char[blockSize];
        ICharIn in = source.newCharIn();
        try {
            ICharOut out = target.newCharOut(openOptions);
            try {
                int limit = maxLength;
                while (limit > 0) {
                    int actual = in.read(block, 0, Math.min(limit, block.length));
                    if (actual == -1)
                        break;
                    limit -= actual;
                    out.write(block, 0, actual);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    /**
     * @seecopy {@link #write(IStreamInputSource)}
     */
    @GeneratedByCopyPaste
    @Override
    public void writeChars(IStreamInputSource source)
            throws IOException {
        if (source == null)
            throw new NullPointerException("source");
        char[] block = new char[blockSize];
        ICharIn in = source.newCharIn();
        try {
            ICharOut out = target.newCharOut(openOptions);
            try {
                while (true) {
                    int actual = in.read(block, 0, block.length);
                    if (actual == -1)
                        break;
                    out.write(block, 0, actual);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }

    @Override
    public void writeChars(Reader in)
            throws IOException {
        write(new ReaderSource(in));
    }

    @Override
    public void writeString(String string)
            throws IOException {
        ICharOut out = target.newCharOut(openOptions);
        try {
            out.write(string);
        } finally {
            out.close();
        }
    }

}

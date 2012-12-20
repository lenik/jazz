package net.bodz.bas.io.resource.tools;

import java.io.IOException;
import java.nio.file.OpenOption;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.IStreamOutputTarget;
import net.bodz.bas.io.resource.IStreamOutputTargetWrapper;
import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;

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
        try {
            IStreamWriting clone = (IStreamWriting) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
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

    @Override
    public int getBlockSize() {
        return blockSize;
    }

    @Override
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
        IByteOut out = target.newByteOut();
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
        ICharOut out = target.newCharOut();
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
            IByteOut out = target.newByteOut();
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
            IByteOut out = target.newByteOut();
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
            ICharOut out = target.newCharOut();
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
            ICharOut out = target.newCharOut();
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
    public void writeString(String string)
            throws IOException {
        ICharOut out = target.newCharOut();
        try {
            out.write(string);
        } finally {
            out.close();
        }
    }

}

package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.adapter.ByteBufferByteIn;
import net.bodz.bas.io.adapter.ByteBufferByteOut;
import net.bodz.bas.meta.decl.DefaultImpl;
import net.bodz.bas.meta.decl.NotNull;

public class ByteArrayResource
        extends AbstractBinaryResource<ByteArrayResource> {

    private final byte[] array;
    private final int start;
    private final int end;

    /**
     * @throws NullPointerException
     *             If <code>array</code> is <code>null</code>.
     */
    public ByteArrayResource(byte[] array) {
        this(array, 0, array.length);
    }

    /**
     * @throws NullPointerException
     *             If <code>array</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If either <code>off</code> or <code>len</code> is out of range.
     */
    public ByteArrayResource(byte[] array, int offset, int length) {
        if (array == null) throw new NullPointerException("array");
        if (offset < 0 || offset > array.length) throw new IndexOutOfBoundsException("Bad offset: " + offset);
        if (length < 0 || (offset + length) > array.length)
            throw new IndexOutOfBoundsException("Bad length: " + length);
        this.array = array;
        this.start = offset;
        this.end = offset + length;
    }

    public byte[] getArray() {
        return array;
    }

    public int getArrayOffset() {
        return start;
    }

    public int size() {
        return end - start;
    }

    @Override
    public Long getLength() {
        return (long) (end - start);
    }

    @NotNull
    @DefaultImpl(ByteBufferByteIn.class)
    @Override
    public IByteIn newByteIn(OpenOption... options)
            throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(array, start, end - start);
        return new ByteBufferByteIn(byteBuffer);
    }

    @NotNull
    @Override
    public IByteOut newByteOut(OpenOption... options)
            throws IOException {

        boolean append = OpenOptions.isAppend(options);
        if (append) // XXX ByteArrayResource append mode
            throw new UnsupportedOperationException();

        ByteBuffer byteBuffer = ByteBuffer.wrap(array, start, end - start);
        return new ByteBufferByteOut(byteBuffer);
    }

    // XXX byte/char IOS, seeker, cropper

    @Override
    protected ISeekable getSeeker() {
        return null;
    }

    @Override
    protected ICroppable getCropper() {
        return null;
    }

}

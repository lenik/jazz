package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.nio.OpenOptions;
import net.bodz.bas.io.IByteIOS;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIOS;
import net.bodz.bas.io.ICroppable;
import net.bodz.bas.io.ISeekable;
import net.bodz.bas.io.adapter.ByteBufferByteIn;
import net.bodz.bas.io.adapter.ByteBufferByteOut;

public class ByteArrayResource
        extends AbstractBinaryResource {

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
        if (array == null)
            throw new NullPointerException("array");
        if (offset < 0 || offset > array.length)
            throw new IndexOutOfBoundsException("Bad offset: " + offset);
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
    public long getLength() {
        return end - start;
    }

    @Override
    public boolean isCharPreferred() {
        return false;
    }

    @Override
    public IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(array, start, end - start);
        return new ByteBufferByteIn(byteBuffer);
    }

    @Override
    public IByteOut _newByteOut(OpenOption... options)
            throws IOException {

        boolean append = OpenOptions.isAppend(options);
        if (append) // XXX ByteArrayResource append mode
            throw new UnsupportedOperationException();

        ByteBuffer byteBuffer = ByteBuffer.wrap(array, start, end - start);
        return new ByteBufferByteOut(byteBuffer);
    }

    // XXX byte/char IOS, seeker, cropper

    @Override
    protected IByteIOS _newByteIOS(OpenOption... options)
            throws IOException {
        return null;
    }

    @Override
    protected ICharIOS _newCharIOS(OpenOption... options)
            throws IOException {
        return null;
    }

    @Override
    protected ISeekable getSeeker() {
        return null;
    }

    @Override
    protected ICroppable getCropper() {
        return null;
    }

}

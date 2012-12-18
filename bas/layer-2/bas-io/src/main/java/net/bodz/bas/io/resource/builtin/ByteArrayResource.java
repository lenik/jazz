package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.sio.ByteBufferByteIn;
import net.bodz.bas.sio.ByteBufferByteOut;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;

public class ByteArrayResource
        extends AbstractBinaryStreamResource {

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
    public Long getLength() {
        return (long) (end - start);
    }

    @Override
    public IByteIn _newByteIn()
            throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(array, start, end - start);
        return new ByteBufferByteIn(byteBuffer);
    }

    @Override
    public IByteOut _newByteOut(boolean append)
            throws IOException {
        if (append) // XXX ByteArrayResource append mode
            throw new UnsupportedOperationException();
        ByteBuffer byteBuffer = ByteBuffer.wrap(array, start, end - start);
        return new ByteBufferByteOut(byteBuffer);
    }

}

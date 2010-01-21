package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.nio.ByteBuffer;

import net.bodz.bas.io.resource.AbstractStreamResource;
import net.bodz.bas.sio.ByteBufferByteIn;
import net.bodz.bas.sio.ByteBufferByteOut;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;

public class ByteArrayResource
        extends AbstractStreamResource {

    private final byte[] array;
    private final int offset;
    private final int length;

    /**
     * @throws NullPointerException
     *             If <code>array</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If either <code>off</code> or <code>len</code> is out of range.
     */
    public ByteArrayResource(byte[] array, int offset, int length) {
        if (array == null)
            throw new NullPointerException("array");
        if (offset < 0 || offset >= array.length)
            throw new IndexOutOfBoundsException("Bad offset: " + offset);
        if (length < 0 || (offset + length) >= array.length)
            throw new IndexOutOfBoundsException("Bad length: " + length);
        this.array = array;
        this.offset = offset;
        this.length = length;
    }

    public byte[] array() {
        return array;
    }

    public int arrayOffset() {
        return offset;
    }

    public int arrayLength() {
        return length;
    }

    @Override
    public IByteIn newByteIn()
            throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(array, offset, length);
        return new ByteBufferByteIn(byteBuffer);
    }

    @Override
    public IByteOut newByteOut()
            throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(array, offset, length);
        return new ByteBufferByteOut(byteBuffer);
    }

}

package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.nio.CharBuffer;

import net.bodz.bas.sio.CharBufferCharIn;
import net.bodz.bas.sio.CharBufferCharOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;

public class CharArrayResource
        extends AbstractTextStreamResource {

    private final char[] array;
    private final int offset;
    private final int length;

    public CharArrayResource(char[] array) {
        this(array, 0, array.length);
    }

    /**
     * @throws NullPointerException
     *             If <code>array</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If either <code>off</code> or <code>len</code> is out of range.
     */
    public CharArrayResource(char[] array, int offset, int length) {
        if (array == null)
            throw new NullPointerException("array");
        if (offset < 0 || offset > array.length)
            throw new IndexOutOfBoundsException("Bad offset: " + offset);
        if (length < 0 || (offset + length) > array.length)
            throw new IndexOutOfBoundsException("Bad length: " + length);
        this.array = array;
        this.offset = offset;
        this.length = length;
    }

    public char[] array() {
        return array;
    }

    public int arrayOffset() {
        return offset;
    }

    public int arrayLength() {
        return length;
    }

    @Override
    public ICharIn _newCharIn()
            throws IOException {
        CharBuffer charBuffer = CharBuffer.wrap(array, offset, length);
        return new CharBufferCharIn(charBuffer);
    }

    @Override
    public ICharOut _newCharOut(boolean append)
            throws IOException {
        // XXX CharArrayResource append
        CharBuffer charBuffer = CharBuffer.wrap(array, offset, length);
        return new CharBufferCharOut(charBuffer);
    }

}

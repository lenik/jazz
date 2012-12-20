package net.bodz.bas.io.resource.builtin;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.OpenOption;

import net.bodz.bas.sio.CharBufferCharIn;
import net.bodz.bas.sio.CharBufferCharOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;

public class CharArrayResource
        extends AbstractTextStreamResource {

    private final char[] array;
    private final int start;
    private final int end;

    public CharArrayResource(char[] array) {
        this(array, 0, array.length);
    }

    /**
     * @throws NullPointerException
     *             If <code>array</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     *             If either <code>off</code> or <code>len</code> is out of range.
     */
    public CharArrayResource(char[] array, int off, int len) {
        if (array == null)
            throw new NullPointerException("array");
        if (off < 0 || off > array.length)
            throw new IndexOutOfBoundsException("Bad off: " + off);
        if (len < 0 || (off + len) > array.length)
            throw new IndexOutOfBoundsException("Bad len: " + len);
        this.array = array;
        this.start = off;
        this.end = off + len;
    }

    public char[] getArray() {
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
    public ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        CharBuffer charBuffer = CharBuffer.wrap(array, start, end - start);
        return new CharBufferCharIn(charBuffer);
    }

    @Override
    public ICharOut _newCharOut(OpenOption... options)
            throws IOException {
        // XXX CharArrayResource append
        CharBuffer charBuffer = CharBuffer.wrap(array, start, end - start);
        return new CharBufferCharOut(charBuffer);
    }

}

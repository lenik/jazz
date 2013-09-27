package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.file.OpenOption;

import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.adapter.CharBufferCharIn;
import net.bodz.bas.io.adapter.CharBufferCharOut;

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
    public long getLength() {
        return end - start;
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

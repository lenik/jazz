package net.bodz.bas.commons.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

import net.bodz.bas.types.buf.BuffersTest;

/**
 * @test {@link BuffersTest}
 */
public class Buffers {

    public static byte[] getBytes(ByteBuffer buffer) {
        return Arrays.copyOfRange(buffer.array(), //
                buffer.arrayOffset() + buffer.position(), buffer.remaining());
    }

    public static String getString(ByteBuffer buffer) throws UnsupportedEncodingException {
        return new String(buffer.array(), //
                buffer.arrayOffset() + buffer.position(), buffer.remaining());
    }

    public static String getString(ByteBuffer buffer, Charset charset) {
        return new String(buffer.array(), //
                buffer.arrayOffset() + buffer.position(), buffer.remaining(), charset);
    }

    public static String getString(ByteBuffer buffer, String charset) throws UnsupportedEncodingException {
        return new String(buffer.array(), //
                buffer.arrayOffset() + buffer.position(), buffer.remaining(), charset);
    }

    public static ByteBuffer _insert(ByteBuffer dest, int destOff, byte[] src) {
        return _insert(dest, destOff, src, 0, src.length, false);
    }

    public static ByteBuffer _insert(ByteBuffer dest, int destOff, byte[] src, boolean beforeCursor) {
        return _insert(dest, destOff, src, 0, src.length, beforeCursor);
    }

    public static ByteBuffer _insert(ByteBuffer dest, int destOff, byte[] src, int off, int len) {
        return _insert(dest, destOff, src, off, len, false);
    }

    /**
     * @return result dest, the original dest must be .
     * @throws NullPointerException
     *             if dest or src is <code>null</code>.
     * @throws UnsupportedOperationException
     */
    public static ByteBuffer _insert(ByteBuffer dest, int destOff, byte[] src, int off, int len, boolean beforeCursor) {
        if (len == 0)
            return dest;
        byte[] _dest = dest.array();
        int limit = dest.limit();
        if (len > dest.capacity() - limit)
            throw new BufferOverflowException();
        int _base = dest.arrayOffset();
        int _destOff = destOff + _base;
        System.arraycopy(_dest, _destOff, _dest, _destOff + len, limit - destOff);
        System.arraycopy(src, off, _dest, _destOff, len);
        ByteBuffer expansion = ByteBuffer.wrap(_dest, _base, _dest.length - _base).slice();
        int position = dest.position();
        if (destOff < position)
            position += len;
        else if (destOff == position && beforeCursor)
            position += len;
        expansion.position(position);
        expansion.limit(limit + len);
        return expansion;
    }

}

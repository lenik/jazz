package net.bodz.bas.t.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

import net.bodz.bas.meta.decl.NotNull;

public interface IByteBuffer
        extends IBuffer<Byte> {

    @NotNull
    byte[] getBackedArray();

    int getBackedArrayOffset();

    default ByteBuffer asByteBuffer() {
        byte[] backedArray = getBackedArray();
        int backedArrayOffset = getBackedArrayOffset();
        int length = length();
        return ByteBuffer.wrap(backedArray, backedArrayOffset, length);
    }

    void append(byte element);

    default void append(int element) {
        append((byte) element);
    }

    default void append(byte[] buf) {
        append(buf, 0, buf.length);
    }

    void append(byte[] buf, int off, int len);

    void append(ByteBuffer buf);

    default void append(IByteBuffer buf) {
        append(buf, 0, buf.length());
    }

    void append(IByteBuffer buf, int off, int len);

    default void shiftTo(@NotNull IByteBuffer dst) {
        dst.append(this);
        this.clear();
    }

    default void shiftFrom(@NotNull IByteBuffer src) {
        src.shiftTo(this);
    }

    default void shiftTo(@NotNull ICharBuffer dst, @NotNull Charset charset)
            throws CharacterCodingException {
        CharsetDecoder decoder = charset.newDecoder();
        decoder.onMalformedInput(CodingErrorAction.REPLACE);

        CharBuffer decoded;
        decoded = decoder.decode(asByteBuffer());

        dst.append(decoded);
        this.clear();
    }

    default void shiftFrom(@NotNull ICharBuffer src, @NotNull Charset charset)
            throws CharacterCodingException {
        src.shiftTo(this, charset);
    }

    default int get(int pos, byte[] buf) {
        return get(pos, buf, 0, buf.length);
    }

    int get(int pos, byte[] buf, int off, int len);

    default int get(int pos, ByteBuffer buf) {
        return get(pos, buf, buf.remaining());
    }

    int get(int pos, ByteBuffer buf, int len);

    default int set(int pos, byte[] buf) {
        return set(pos, buf, 0, buf.length);
    }

    int set(int pos, byte[] buf, int off, int len);

    default int set(int pos, ByteBuffer buf) {
        return set(pos, buf, buf.remaining());
    }

    int set(int pos, ByteBuffer buf, int len);

    byte getByte(int pos);

    void setByte(int pos, byte value);

    byte[] toByteArray();

}

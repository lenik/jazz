package net.bodz.bas.t.buffer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;

import net.bodz.bas.meta.decl.NotNull;

public interface ICharBuffer
        extends IBuffer<Character> {

    @NotNull
    char[] getBackedArray();

    int getBackedArrayOffset();

    @NotNull
    default CharBuffer asCharBuffer() {
        char[] backedArray = getBackedArray();
        int backedArrayOffset = getBackedArrayOffset();
        int length = length();
        return CharBuffer.wrap(backedArray, backedArrayOffset, length);
    }

    void append(char element);

    default void append(int element) {
        append((char) element);
    }

    default void append(char[] buf) {
        append(buf, 0, buf.length);
    }

    void append(char[] buf, int off, int len);

    void append(CharBuffer buf);

    default void append(ICharBuffer buf) {
        append(buf, 0, buf.length());
    }

    void append(ICharBuffer buf, int off, int len);

    default void shiftTo(@NotNull ICharBuffer dst) {
        dst.append(this);
        this.clear();
    }

    default void shiftFrom(@NotNull ICharBuffer src) {
        src.shiftTo(this);
    }

    default void shiftTo(@NotNull IByteBuffer dst, @NotNull Charset charset)
            throws CharacterCodingException {
        CharsetEncoder encoder = charset.newEncoder();
        encoder.onMalformedInput(CodingErrorAction.REPLACE);

        ByteBuffer encoded;
        encoded = encoder.encode(asCharBuffer());

        dst.append(encoded);
        this.clear();
    }

    default void shiftFrom(@NotNull IByteBuffer src, @NotNull Charset charset)
            throws CharacterCodingException {
        src.shiftTo(this, charset);
    }

    default int get(int pos, char[] buf) {
        return get(pos, buf, 0, buf.length);
    }

    int get(int pos, char[] buf, int off, int len);

    default int get(int pos, CharBuffer buf) {
        return get(pos, buf, buf.remaining());
    }

    int get(int pos, CharBuffer buf, int len);

    default int set(int pos, char[] buf) {
        return set(pos, buf, 0, buf.length);
    }

    int set(int pos, char[] buf, int off, int len);

    default int set(int pos, CharBuffer buf) {
        return set(pos, buf, buf.remaining());
    }

    int set(int pos, CharBuffer buf, int len);

    char getChar(int pos);

    void setChar(int pos, char value);

    char[] toCharArray();

    default byte[] toByteArray() {
        return toByteArray(StandardCharsets.UTF_8);
    }

    byte[] toByteArray(Charset charset);

}

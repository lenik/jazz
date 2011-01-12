package net.bodz.bas.text.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.util.exception.DecodeException;
import net.bodz.bas.util.exception.EncodeException;

public interface IByteCodec
        extends ISimpleByteCodec {

    Charset getPreferredCharsetForEncodedContents();

    String encode(byte[] bytes)
            throws EncodeException;

    byte[] decode(char[] chars)
            throws DecodeException;

    byte[] decode(String string)
            throws DecodeException;

    /**
     * @throws NullPointerException
     *             If <code>bytes</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     */
    String encode(byte[] bytes, int off, int len)
            throws EncodeException;

    /**
     * @throws NullPointerException
     *             If <code>chars</code> is <code>null</code>.
     * @throws IndexOutOfBoundsException
     */
    byte[] decode(char[] chars, int off, int len)
            throws DecodeException;

    byte[] decode(String string, int start, int end)
            throws DecodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void encode(InputStream in, Writer out)
            throws IOException, EncodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void decode(Reader in, OutputStream out)
            throws IOException, DecodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void encode(ByteBuffer in, CharBuffer out)
            throws IOException, EncodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void decode(CharBuffer in, ByteBuffer out)
            throws IOException, DecodeException;

}

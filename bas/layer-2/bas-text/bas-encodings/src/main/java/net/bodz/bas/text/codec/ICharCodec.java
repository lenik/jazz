package net.bodz.bas.text.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import net.bodz.bas.exceptions.DecodeException;
import net.bodz.bas.exceptions.EncodeException;

public interface ICharCodec
        extends ISimpleCharCodec {

    Charset getPreferredCharsetForEncodedContents();

    byte[] encode(String string)
            throws EncodeException;

    byte[] encode(char[] chars)
            throws EncodeException;

    String decode(byte[] bytes)
            throws DecodeException;

    /**
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    byte[] encode(char[] chars, int off, int len)
            throws EncodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     * @throws IndexOutOfBoundsException
     */
    String decode(byte[] bytes, int off, int len)
            throws DecodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void encode(Reader in, OutputStream out)
            throws IOException, EncodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void decode(InputStream in, Writer out)
            throws IOException, DecodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void encode(CharBuffer in, ByteBuffer out)
            throws IOException, EncodeException;

    /**
     * @throws NullPointerException
     *             If any argument is <code>null</code>.
     */
    void decode(ByteBuffer in, CharBuffer out)
            throws IOException, DecodeException;

}

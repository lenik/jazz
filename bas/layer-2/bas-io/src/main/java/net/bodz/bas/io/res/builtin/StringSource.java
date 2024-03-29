package net.bodz.bas.io.res.builtin;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.nio.file.OpenOption;

import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.StringCharIn;
import net.bodz.bas.io.impl.EncodedByteIn;
import net.bodz.bas.io.res.AbstractStreamInputSource;

public class StringSource
        extends AbstractStreamInputSource {

    private final String string;

    /**
     * @throws NullPointerException
     *             If <code>string</code> is <code>null</code>.
     */
    public StringSource(String string) {
        if (string == null)
            throw new NullPointerException("string");
        this.string = string;
    }

    @Override
    public boolean isCharPreferred() {
        return true;
    }

    @Override
    public ICharIn _newCharIn(OpenOption... options)
            throws IOException {
        return new StringCharIn(string);
    }

    /**
     *
     */
    // (For get more test results, using the converted byte-in.)
    // @Override
    // public IByteIn newByteIn()
    // throws IOException {
    // byte[] bytes = string.getBytes(getCharset());
    // ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
    // return new ByteBufferByteIn(byteBuffer);
    // }

    @Override
    public IByteIn _newByteIn(OpenOption... options)
            throws IOException {
        ICharIn charIn = _newCharIn(options);
        CharsetEncoder encoder = getCharset().newEncoder();
        return new EncodedByteIn(charIn, encoder);
    }

}

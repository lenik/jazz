package net.bodz.bas.text.codec.builtin;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.sio.ByteInInputStream;
import net.bodz.bas.sio.ByteOutOutputStream;
import net.bodz.bas.sio.CharInReader;
import net.bodz.bas.sio.CharOutWriter;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.codec.AbstractByteCodec;
import net.bodz.bas.util.exception.DecodeException;
import net.bodz.bas.util.exception.EncodeException;
import net.bodz.bas.util.exception.NotImplementedException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Codec
        extends AbstractByteCodec {

    private static BASE64Encoder encoder;
    private static BASE64Decoder decoder;
    static {
        encoder = new BASE64Encoder();
        decoder = new BASE64Decoder();
    }

    public Base64Codec() {
        super(5, 8);
    }

    static final Charset base64Charset = Charset.forName("ASCII");

    @Override
    public Charset getPreferredCharsetForEncodedContents() {
        return base64Charset;
    }

    @Override
    public void encode(IByteIn byteIn, ICharOut charOut)
            throws IOException, EncodeException {
        ByteInInputStream in = new ByteInInputStream(byteIn);
        CharOutWriter out = new CharOutWriter(charOut);
        throw new NotImplementedException();
    }

    @Override
    public void decode(ICharIn charIn, IByteOut byteOut)
            throws IOException, DecodeException {
        CharInReader in = new CharInReader(charIn);
        ByteOutOutputStream out = new ByteOutOutputStream(byteOut);
        throw new NotImplementedException();
    }

    static final Base64Codec instance = new Base64Codec();

    public static Base64Codec getInstance() {
        return instance;
    }

}

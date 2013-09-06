package net.bodz.bas.data.codec.builtin;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

import net.bodz.bas.data.codec.AbstractByteCodec;
import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.EncodeException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;

public class Base64Codec
        extends AbstractByteCodec {

    static final int blockSize = 1;
    static final int encodeBlockSize = 3 * blockSize; // 3*8bit => 4*6bit
    static final int decodeBlockSize = 4 * blockSize; // 4*6bit => 3*8bit

    public Base64Codec() {
        super(3, 4);
    }

    static final Charset base64Charset = Charset.forName("ASCII");

    @Override
    public Charset getPreferredCharsetForEncodedContents() {
        return base64Charset;
    }

    @Override
    public void encode(IByteIn byteIn, ICharOut charOut)
            throws IOException, EncodeException {
        Base64 impl = new Base64(); // chunk_size, line_sep, url_safe?
        byte[] ibuf = new byte[encodeBlockSize];
        char[] obuf = new char[decodeBlockSize];
        int cb;
        while ((cb = byteIn.read(ibuf)) != -1) {
            byte[] trim; // fix
            if (cb == ibuf.length)
                trim = ibuf;
            else {
                trim = new byte[cb];
                System.arraycopy(ibuf, 0, trim, 0, cb);
            }
            byte[] b64 = impl.encode(trim);
            int cc = b64.length;

            // fast-convert
            for (int i = 0; i < cc; i++)
                obuf[i] = (char) b64[i];
            charOut.write(obuf, 0, cc);
        }
    }

    @Override
    public void decode(ICharIn charIn, IByteOut byteOut)
            throws IOException, DecodeException {
        Base64 impl = new Base64(); // chunk_size, line_sep, url_safe?
        char[] ibuf = new char[decodeBlockSize];
        // byte[] obuf = new byte[encodeBlockSize];
        int cc;
        while ((cc = charIn.read(ibuf)) != -1) {
            byte[] trim = new byte[cc]; // fix & fast-convert
            for (int i = 0; i < cc; i++)
                trim[i] = (byte) ibuf[i];

            byte[] raw = impl.decode(trim);
            byteOut.write(raw);
        }
    }

    static final Base64Codec instance = new Base64Codec();

    public static Base64Codec getInstance() {
        return instance;
    }

}

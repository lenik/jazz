package net.bodz.bas.text.codec.builtin;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.exceptions.EncodeException;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.text.codec.AbstractByteCodec;
import net.bodz.bas.text.model.CharFeature;

public class HexCodec
        extends AbstractByteCodec {

    private final String IFS;
    private final char simpleIFS;
    private final char[] enctbl = CharFeature.n2cl;
    private final byte[] dectbl = CharFeature.c2n;

    private final int width;
    private final String NL;

    public HexCodec() {
        this(" ");
    }

    public HexCodec(String IFS) {
        this(IFS, 0, null);
    }

    public HexCodec(String IFS, int width, String NL) {
        super(1, 2 + IFS.length());
        this.IFS = IFS;
        this.simpleIFS = IFS.length() == 0 ? 0 : IFS.charAt(0);
        this.width = width;
        this.NL = NL;
    }

    static final Charset hexCharset = Charset.forName("ASCII");

    @Override
    public Charset getPreferredCharsetForEncodedContents() {
        return hexCharset;
    }

    @Override
    public void encode(IByteIn in, ICharOut out)
            throws IOException, EncodeException {
        char[] buf = new char[2];
        boolean first = true;
        int col = 0;
        for (int b = in.read(); b != -1; b = in.read()) {
            if (first)
                first = false;
            else
                out.write(IFS);
            buf[0] = enctbl[b >> 4];
            buf[1] = enctbl[b & 0xF];
            out.write(buf);
            if (width != 0) {
                if (++col >= width) {
                    out.write(NL);
                    col = 0;
                    first = true;
                }
            }
        }
    }

    @Override
    public void decode(ICharIn in, IByteOut out)
            throws IOException, EncodeException {
        int byt = 0;
        int digits = 0;
        for (int c = in.read(); c != -1; c = in.read()) {
            if (c == simpleIFS || Character.isWhitespace((char) c)) {
                if (digits != 0) {
                    out.write(byt);
                    byt = 0;
                    digits = 0;
                }
                continue;
            }
            byte hexval = dectbl[c];
            if (digits == 0) {
                byt = hexval;
                digits = 1;
            } else {
                byt = (byt << 4) | hexval;
                out.write(byt);
                byt = 0;
                digits = 0;
            }
        }
        if (digits != 0)
            out.write(byt);
    }

    static final HexCodec instance = new HexCodec();

    /**
     * The default hex codec using ' ' (space) as byte separator.
     */
    public static HexCodec getInstance() {
        return instance;
    }

}

package net.bodz.bas.text.encodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.text.charsets.Lookups;

public class HexEncoding extends AbstractEncoding {

    private final String IFS;
    private final char simpleIFS;
    private final char[] enctbl = Lookups.n2cl;
    private final byte[] dectbl = Lookups.c2n;

    private final int width;
    private final String NL;

    public HexEncoding() {
        this(" "); 
    }

    public HexEncoding(String IFS) {
        this(IFS, 0, null);
    }

    public HexEncoding(String IFS, int width, String NL) {
        super(1, 2 + IFS.length());
        this.IFS = IFS;
        this.simpleIFS = IFS.length() == 0 ? 0 : IFS.charAt(0);
        this.width = width;
        this.NL = NL;
    }

    /** bin2hex */
    @Override
    public void encode(InputStream in, Writer out) throws IOException {
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

    /** hex2bin */
    @Override
    public void decode(Reader in, OutputStream out) throws IOException, ParseException {
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

}

package net.bodz.bas.text.encodings;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.text.util.Lookups;

public class HexEncoding extends _Encoding {

    private final String space;
    private final char[] enctbl = Lookups.n2cl;
    private final byte[] dectbl = Lookups.c2n;

    private final int    width;
    private final String sep;

    public HexEncoding() {
        this(" ");
    }

    public HexEncoding(String space) {
        this(space, 0, null);
    }

    public HexEncoding(String space, int width, String sep) {
        super(1, 2 + space.length());
        this.space = space;
        this.width = width;
        this.sep = sep;
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
                out.write(space);
            buf[0] = enctbl[b >> 4];
            buf[1] = enctbl[b & 0xF];
            out.write(buf);
            if (width != 0) {
                if (++col >= width) {
                    out.write(sep);
                    col = 0;
                    first = true;
                }
            }
        }
    }

    /** hex2bin */
    @Override
    public void decode(Reader in, OutputStream out) throws IOException,
            ParseException {
        int byt = 0;
        boolean half = false;
        for (int c = in.read(); c != -1; c = in.read()) {
            if (Character.isWhitespace((char) c)) {
                if (half) {
                    out.write(byt);
                    byt = 0;
                    half = false;
                }
                continue;
            }
            byte hexval = dectbl[c];
            if (half) {
                out.write((byt << 4) | hexval);
                byt = 0;
                half = false;
            } else {
                byt = hexval;
                half = true;
            }
        }
        if (half)
            out.write(byt);
    }

}

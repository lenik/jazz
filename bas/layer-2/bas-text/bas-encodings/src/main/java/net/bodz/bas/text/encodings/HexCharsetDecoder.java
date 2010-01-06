package net.bodz.bas.text.encodings;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class HexCharsetDecoder extends CharsetDecoder {

    private byte[] transtbl = Lookups.c2n;
    private byte delimiter = 0x20;
    private int ch;
    private boolean chFill;

    /**
     * <ul>
     * <li>"41 " -> 'A' 1/3 chars
     * <li>"554A " -> '啊' 1/5 chars
     * <li>"DACCDEBB " -> '?' 1/9 chars
     * </ul>
     */
    public HexCharsetDecoder(Charset charset) {
        super(charset, 0.25f, 1f);
    }

    @Override
    protected CoderResult decodeLoop(ByteBuffer in, CharBuffer out) {
        int hexchr;
        // System.err.println(in.position() + "," + in.limit());

        try {
            while (in.hasRemaining()) {
                hexchr = IntMath.unsign(in.get());

                // System.err.print(""+Integer.toHexString((int)b)+ " ");
                if (hexchr == delimiter) {
                    if (chFill) {
                        // avoid of output many '\0's.
                        // System.err.print("" + (char)c + " ");
                        out.put((char) ch);
                        chFill = false;
                    }
                    ch = 0;
                    continue;
                }

                int hexval = transtbl[hexchr];
                if (hexval == -1) {
                    if (ch != 0) {
                        out.put((char) ch);
                    }

                    // N@ any better way??
                    in.position(in.position() - 1);
                    return CoderResult.unmappableForLength(1);
                } else if (hexval >= 32) {
                    if (ch != 0) {
                        out.put((char) ch);
                    }
                    out.put((char) hexval);
                    chFill = false;
                    ch = 0;
                    continue;
                }
                // System.err.print(""+digit+ " ");
                ch = ch * 16 + hexval;
                chFill = true;
            }

            // input ended, output the last character if the stream
            // isn't ended with 0x20.
            // HOWEVER, this is disabled cuz it will become ambigious
            // within segmentized parsing.
            /*
             * if (c != 0) { out.put((char)c); c = 0; iDigits = 0; }
             */
            return CoderResult.UNDERFLOW;
        } catch (BufferOverflowException e) {
            return CoderResult.OVERFLOW;
        }
    }

    @Override
    protected void implReset() {
        ch = 0;
        chFill = false;
    }

    @Override
    protected CoderResult implFlush(CharBuffer out) {
        if (chFill) {
            out.put((char) ch);
            ch = 0;
            chFill = false;
        }
        return CoderResult.UNDERFLOW;
    }

}

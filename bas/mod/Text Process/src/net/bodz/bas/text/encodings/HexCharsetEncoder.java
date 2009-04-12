package net.bodz.bas.text.encodings;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import net.bodz.bas.text.util.Lookups;

/**
 * Encodes each character(maybe latin-1, utf-8, ucs-4, etc..) into hex string;
 * space(0x20) is used for seperate each character, internally.
 */
public class HexCharsetEncoder extends CharsetEncoder {

    private byte[]  transtbl;
    private byte[]  delimiter = { 0x20 };
    private boolean leads     = false;

    public HexCharsetEncoder(Charset charset) {
        this(charset, Lookups.n2cl);
    }

    /**
     * <ul>
     * <li>min: 1 ascii char 'A' -> 2 bytes '41'
     * <li>max1: 1 wide char '啊' -> 4 bytes '554A'
     * <li>max2 (surr. pair) 8 bytes
     * </ul>
     * 
     * @param transtbl
     *            : int ord -> (byte) char
     */
    public HexCharsetEncoder(Charset charset, char[] transtbl) {
        super(charset, 3.0f + transtbl.length, 8.0f + transtbl.length, //
                " ? ".getBytes(Charsets.ASCII)); //$NON-NLS-1$
        this.transtbl = new byte[transtbl.length];
        for (int i = 0; i < transtbl.length; i++)
            this.transtbl[i] = (byte) transtbl[i];
    }

    public HexCharsetEncoder(Charset cs, float averageBytesPerChar,
            float maxBytesPerChar, byte[] replacement) {
        super(cs, averageBytesPerChar, maxBytesPerChar, replacement);
    }

    @Override
    public boolean canEncode(char c) {
        return true;
    }

    @Override
    protected CoderResult encodeLoop(CharBuffer in, ByteBuffer out) {
        byte[] buf = new byte[8];

        while (in.hasRemaining()) {
            int c = in.get();
            int i = 8;
            do {
                int digit = c % 16;
                c = c >> 4;
                i--;
                buf[i] = transtbl[digit];
            } while (c > 0);
            if (leads)
                out.put(delimiter);
            out.put(buf, i, buf.length - i);
            leads = true;
        }
        return CoderResult.UNDERFLOW;
    }

    @Override
    protected void implReset() {
        leads = false;
    }

}

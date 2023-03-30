package net.bodz.bas.io.data;

import java.io.IOException;
import java.nio.charset.Charset;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.BCharIn;

public class CharsetUtils {

    public static int computeCharLen(char ch, Charset charset)
            throws ParseException {
        char[] array = { ch };
        BCharIn src = new BCharIn(array);
        CharEncoder enc = new CharEncoder(charset, src);
        try {
            byte[] charBytes = enc.encodeChar();
            return charBytes.length;
        } catch (IOException e) {
            throw new UnexpectedException(e);
        }
    }

}

package net.bodz.bas.net.util;

import java.nio.charset.Charset;

import net.bodz.bas.fn.ByteSeqMatcher;
import net.bodz.bas.fn.CharSeqMatcher;

public class ReadUntils {

    public static CharSeqMatcher chars(char... chars) {
        return new CharSeqMatcher(chars);
    }

    public static CharSeqMatcher string(String string) {
        return new CharSeqMatcher(string);
    }

    public static ByteSeqMatcher bytes(byte[] pattern) {
        return new ByteSeqMatcher(pattern);
    }

    public static ByteSeqMatcher bytes(String string) {
        return new ByteSeqMatcher(string);
    }

    public static ByteSeqMatcher bytes(String string, Charset charset) {
        return new ByteSeqMatcher(string, charset);
    }

}

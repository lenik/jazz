package net.bodz.bas.c.java.nio;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import net.bodz.bas.err.IllegalArgumentTypeException;

public class Charsets {

    public static Charset DEFAULT = Charset.defaultCharset();
    public static Charset ASCII = Charset.forName("ascii");
    public static Charset UTF8 = Charset.forName("utf-8");

    public static CharsetEncoder getEncoder(Object charset) {
        if (charset == null)
            return DEFAULT.newEncoder();
        if (charset instanceof CharsetEncoder)
            return (CharsetEncoder) charset;
        if (charset instanceof Charset)
            return ((Charset) charset).newEncoder();
        if (charset instanceof String)
            return Charset.forName((String) charset).newEncoder();
        throw new IllegalArgumentTypeException(charset);
    }

    public static CharsetDecoder getDecoder(Object charset) {
        if (charset == null)
            return DEFAULT.newDecoder();
        if (charset instanceof CharsetDecoder)
            return (CharsetDecoder) charset;
        if (charset instanceof Charset)
            return ((Charset) charset).newDecoder();
        if (charset instanceof String)
            return Charset.forName((String) charset).newDecoder();
        throw new IllegalArgumentTypeException(charset);
    }

}

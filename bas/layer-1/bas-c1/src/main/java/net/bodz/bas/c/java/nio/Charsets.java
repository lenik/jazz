package net.bodz.bas.c.java.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalArgumentTypeException;
import net.bodz.bas.err.ParseException;

public class Charsets {

    public static Charset DEFAULT = Charset.defaultCharset();

    public static final Charset ASCII7 = Charset.forName("ascii");
    public static final Charset ASCII8 = Charset.forName("iso-8859-1");
    public static final Charset UTF8 = Charset.forName("utf-8");
    public static final Charset UTF16_LE = Charset.forName("utf-16le");
    public static final Charset UTF16_BE = Charset.forName("utf-16be");

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

    static Map<Charset, Map<Character, byte[]>> charEncodingCache = new HashMap<>();
    static Map<Charset, Map<ByteBuffer, Character>> charDecodingCache = new HashMap<>();

    public static Map<Character, byte[]> getCharEncodingMap(Charset charset) {
        Map<Character, byte[]> map = charEncodingCache.get(charset);
        if (map == null) {
            map = new HashMap<>();
            charEncodingCache.put(charset, map);
        }
        return map;
    }

    public static Map<ByteBuffer, Character> getCharDecodingMap(Charset charset) {
        Map<ByteBuffer, Character> map = charDecodingCache.get(charset);
        if (map == null) {
            map = new HashMap<>();
            charDecodingCache.put(charset, map);
        }
        return map;
    }

    public static byte[] encodeChar(Charset charset, char ch) {
        Map<Character, byte[]> map = getCharEncodingMap(charset);
        byte[] bytes = map.get(ch);
        if (bytes == null) {
            bytes = String.valueOf(ch).getBytes();
            map.put(ch, bytes);
        }
        return bytes;
    }

    public static Character decodeChar(Charset charset, byte[] bytes)
            throws ParseException {
        return decodeChar(charset, bytes, 0, bytes.length);
    }

    public static Character decodeChar(Charset charset, byte[] bytes, int off, int len)
            throws ParseException {
        Map<ByteBuffer, Character> map = getCharDecodingMap(charset);
        ByteBuffer bb = ByteBuffer.wrap(bytes, off, len);
        Character ch = map.get(bb);
        if (ch == null) {
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer chars = CharBuffer.allocate(4);
            CoderResult result = decoder.decode(bb, chars, true);
            if (result.isError()) {
                if (result.isMalformed())
                    throw new MalformedInputException(result);
                if (result.isUnmappable())
                    throw new UnmappableCharException(result);
                throw new ParseException(result.toString());
            }
            int nChar = chars.position();
            if (nChar == 0)
                throw new ParseException("no char decoded.");

            chars.flip();
            ch = chars.get();
            if (nChar > 1) {
                chars.flip();
                StringBuilder sb = new StringBuilder(nChar);
                sb.append(ch);
                for (int i = 1; i < nChar; i++)
                    sb.append(chars.get());
                throw new ParseException("more than one char decoded: " + sb);
            }
            bb.clear();
            map.put(bb, ch);
        }
        return ch;
    }

}

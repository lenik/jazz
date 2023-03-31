package net.bodz.bas.c.java.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.IllegalArgumentTypeException;
import net.bodz.bas.err.ParseException;

public class Charsets {

    public static Charset DEFAULT = Charset.defaultCharset();

    public static final Charset US_ASCII = get("US-ASCII", true);
    public static final Charset ISO_8859_1 = get("ISO-8859-1", true);
    public static final Charset UTF_8 = get("UTF-8", true);
    public static final Charset UTF_16 = get("UTF-16", true);
    public static final Charset UTF_16LE = get("UTF-16LE", true);
    public static final Charset UTF_16BE = get("UTF-16BE", true);
    public static final Charset UTF_32 = get("UTF-16", true);
    public static final Charset UTF_32LE = get("UTF-32LE", true);
    public static final Charset UTF_32BE = get("UTF-32BE", true);

    public static final Charset BIG5 = get("Big5", false);
    public static final Charset EUC_KR = get("EUC-KR", false);
    public static final Charset GB2312 = get("GB2312", false);
    public static final Charset GB18030 = get("GB18030", false);
    public static final Charset GBK = get("GBK", false);
    public static final Charset SHIFT_JIS = get("Shift_JIS", false);

    static Charset get(String name, boolean required) {
        if (required) {
            return Charset.forName(name);
        } else {
            try {
                return Charset.forName(name);
            } catch (UnsupportedCharsetException e) {
                return null;
            }
        }
    }

    public static String getCanonicalName(String charsetName) {
        try {
            Charset charset = Charset.forName(charsetName);
            return charset.name();
        } catch (UnsupportedCharsetException e) {
            return null;
        }
    }

    public static void printCanonicalNames() {
        for (String name : Charset.availableCharsets().keySet()) {
            Charset charset = Charset.forName(name);
            System.out.println(name + ": ");
            for (String alias : charset.aliases())
                System.out.println("    alias: " + alias);
        }
    }

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

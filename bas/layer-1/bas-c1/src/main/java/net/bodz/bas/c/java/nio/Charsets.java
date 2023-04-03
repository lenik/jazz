package net.bodz.bas.c.java.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.IllegalArgumentTypeException;
import net.bodz.bas.err.ParseException;

public class Charsets {

    private static Set<String> declaredCharsetNames = new HashSet<>();

    public static Charset DEFAULT = Charset.defaultCharset();

    public static final Charset US_ASCII = declare("US-ASCII", true);
    public static final Charset ISO_8859_1 = declare("ISO-8859-1", true);
    public static final Charset UTF_8 = declare("UTF-8", true);
    public static final Charset UTF_16 = declare("UTF-16", true);
    public static final Charset UTF_16LE = declare("UTF-16LE", true);
    public static final Charset UTF_16BE = declare("UTF-16BE", true);
    public static final Charset UTF_32 = declare("UTF-16", true);
    public static final Charset UTF_32LE = declare("UTF-32LE", true);
    public static final Charset UTF_32BE = declare("UTF-32BE", true);

    public static final Charset BIG5 = declare("Big5", false);
    public static final Charset EUC_KR = declare("EUC-KR", false);
    public static final Charset GB2312 = declare("GB2312", false);
    public static final Charset GB18030 = declare("GB18030", false);
    public static final Charset GBK = declare("GBK", false);
    public static final Charset SHIFT_JIS = declare("Shift_JIS", false);

    static Charset declare(String name, boolean required) {
        Charset charset;
        if (required) {
            charset = Charset.forName(name);
        } else {
            try {
                charset = Charset.forName(name);
            } catch (UnsupportedCharsetException e) {
                return null;
            }
        }
        String canonicalName = charset.name();
        declaredCharsetNames.add(canonicalName);
        return charset;
    }

    public static String getDeclaredName(String charsetName) {
        return getDeclaredName(charsetName, false);
    }

    public static String getDeclaredName(Charset charset) {
        return getDeclaredName(charset, false);
    }

    public static String getDeclaredName(String charsetName, boolean includeClassName) {
        Charset charset;
        try {
            charset = Charset.forName(charsetName);
        } catch (UnsupportedCharsetException e) {
            return null;
        }
        return getDeclaredName(charset, includeClassName);
    }

    public static String getDeclaredName(Charset charset, boolean includeClassName) {
        String canonicalName = charset.name();
        if (!declaredCharsetNames.contains(canonicalName))
            return null; // not declared.

        String fieldName = canonicalName.replace('-', '_');
        if (includeClassName)
            return Charsets.class.getSimpleName() + "." + fieldName;
        else
            return fieldName;
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

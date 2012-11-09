package net.bodz.bas.text.charset;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.IllegalArgumentTypeException;

/**
 * Literal Charset Provider
 * 
 * Literal charset is designed for debug or special purpose.
 */
public class BasCharsetProvider
        extends CharsetProvider {

    private Map<String, Charset> charsetImpls = new TreeMap<String, Charset>();

    public BasCharsetProvider() {
        addCharsetImpl(new HexCharset());
    }

    void addCharsetImpl(Charset charset) {
        charsetImpls.put(charset.name(), charset);
    }

    @Override
    public Iterator<Charset> charsets() {
        return charsetImpls.values().iterator();
    }

    @Override
    public Charset charsetForName(String charsetName) {
        for (String name : charsetImpls.keySet()) {
            Charset cs = charsetImpls.get(name);
            if (name.equals(charsetName))
                return cs;
            if (cs.aliases().contains(charsetName))
                return cs;
        }
        return null;
    }

    // utilities

    public static Charset DEFAULT = Charset.defaultCharset();
    public static Charset ASCII = Charset.forName("ascii");

    public static Charset get(Object charset) {
        if (charset == null)
            return DEFAULT;
        if (charset instanceof Charset)
            return (Charset) charset;
        if (charset instanceof String)
            return Charset.forName((String) charset);
        throw new IllegalArgumentTypeException(charset, "String or Charset");
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

}

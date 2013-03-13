package net.bodz.bas.text.charset;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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

}

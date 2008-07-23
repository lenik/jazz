package net.bodz.bas.text.encodings;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Literal Charset Provider Literal charset is designed for debug or special
 * purpose.
 */
public class Charsets extends CharsetProvider {

    static Charset       ASCII     = Charset.forName("ascii");

    HexCharset           hexCharset;

    Map<String, Charset> _charsets = new HashMap<String, Charset>();

    public Charsets() {
        super();
        hexCharset = new HexCharset();

        _charsets.put(hexCharset.name(), hexCharset);
    }

    @Override
    public Iterator<Charset> charsets() {
        return _charsets.values().iterator();
    }

    @Override
    public Charset charsetForName(String charsetName) {
        for (String name : _charsets.keySet()) {
            Charset cs = _charsets.get(name);
            if (name.equals(charsetName))
                return cs;
            if (cs.aliases().contains(charsetName))
                return cs;
        }
        return null;
    }

}

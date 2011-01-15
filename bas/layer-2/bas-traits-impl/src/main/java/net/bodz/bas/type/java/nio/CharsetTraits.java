package net.bodz.bas.type.java.nio;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class CharsetTraits
        extends AbstractCommonTraits<Charset> {

    public CharsetTraits() {
        super(Charset.class);
    }

    @Override
    public Charset parse(String name)
            throws ParseException {
        try {
            return Charset.forName(name);
        } catch (UnsupportedCharsetException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    private static List<String> availableCharsetNames;

    @Override
    public synchronized Charset newSample()
            throws CreateException {
        if (availableCharsetNames == null) {
            Map<String, Charset> availableCharsets = Charset.availableCharsets();
            availableCharsetNames = new ArrayList<String>(availableCharsets.keySet());
        }
        int randomIndex = random.nextInt(availableCharsetNames.size());
        String randomCharsetName = availableCharsetNames.get(randomIndex);
        Charset randomCharset = Charset.forName(randomCharsetName);
        return randomCharset;
    }

}

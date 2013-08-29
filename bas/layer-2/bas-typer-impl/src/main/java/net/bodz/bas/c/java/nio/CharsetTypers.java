package net.bodz.bas.c.java.nio;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class CharsetTypers
        extends AbstractCommonTypers<Charset> {

    public CharsetTypers() {
        super(Charset.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
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
    public Charset newSample()
            throws CreateException {
        if (availableCharsetNames == null) {
            synchronized (CharsetTypers.class) {
                if (availableCharsetNames == null) {
                    Map<String, Charset> availableCharsets = Charset.availableCharsets();
                    availableCharsetNames = new ArrayList<String>(availableCharsets.keySet());
                }
            }
        }
        int randomIndex = random.nextInt(availableCharsetNames.size());
        String randomCharsetName = availableCharsetNames.get(randomIndex);
        Charset randomCharset = Charset.forName(randomCharsetName);
        return randomCharset;
    }

}

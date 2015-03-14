package net.bodz.bas.c.java.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class BooleanTypers
        extends AbstractCommonTypers<Boolean> {

    public BooleanTypers() {
        super(Boolean.class);
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

    static final Map<String, Boolean> valmap;
    static {
        valmap = new HashMap<String, Boolean>();
        valmap.put("true", true);
        valmap.put("yes", true);
        valmap.put("on", true);
        valmap.put("1", true);
        valmap.put("-1", true);
        valmap.put("false", false);
        valmap.put("no", false);
        valmap.put("off", false);
        valmap.put("0", false);
    }

    @Override
    public Boolean parse(String text, IOptions options)
            throws ParseException {
        if (text == null)
            return false; // or null?
        return valmap.get(text.toLowerCase());
    }

    @Override
    public Boolean newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        return prng.nextBoolean();
    }

}

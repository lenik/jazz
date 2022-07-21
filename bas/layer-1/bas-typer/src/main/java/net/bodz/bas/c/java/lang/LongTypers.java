package net.bodz.bas.c.java.lang;

import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class LongTypers
        extends AbstractCommonTypers<Long> {

    public LongTypers() {
        super(Long.class);
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
    public Long parse(String text, IOptions options)
            throws ParseException {
        try {
            return Long.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Long newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        boolean signed = options.getBoolean(OptionNames.signed, true);
        long sample;
        do {
            sample = prng.nextLong();
        } while (!signed && sample < 0);
        return sample;
    }

    public static final LongTypers INSTANCE = new LongTypers();

}

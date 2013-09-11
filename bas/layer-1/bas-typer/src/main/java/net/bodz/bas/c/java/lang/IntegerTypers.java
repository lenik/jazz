package net.bodz.bas.c.java.lang;

import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class IntegerTypers
        extends AbstractCommonTypers<Integer> {

    public IntegerTypers() {
        super(Integer.class);
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
    public Integer parse(String text, IOptions options)
            throws ParseException {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Integer newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        return prng.nextInt();
    }

}

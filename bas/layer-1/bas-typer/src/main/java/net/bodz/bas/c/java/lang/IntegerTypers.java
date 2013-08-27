package net.bodz.bas.c.java.lang;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class IntegerTypers
        extends AbstractCommonTypers<Integer> {

    public IntegerTypers() {
        super(Integer.class);
    }

    @Override
    protected Object _query(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public Integer parse(String text)
            throws ParseException {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Integer newSample()
            throws CreateException {
        return random.nextInt();
    }

}

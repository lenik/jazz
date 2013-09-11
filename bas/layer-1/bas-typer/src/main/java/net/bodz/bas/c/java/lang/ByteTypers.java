package net.bodz.bas.c.java.lang;

import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class ByteTypers
        extends AbstractCommonTypers<Byte> {

    public ByteTypers() {
        super(Byte.class);
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
    public Byte parse(String text, IOptions options)
            throws ParseException {
        try {
            return Byte.valueOf(text);
        } catch (NumberFormatException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Byte newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        return (byte) prng.nextInt();
    }

}

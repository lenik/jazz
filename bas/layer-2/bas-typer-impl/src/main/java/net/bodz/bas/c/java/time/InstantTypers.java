package net.bodz.bas.c.java.time;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class InstantTypers
        extends TemporalCommonTypers<Instant> {

    public InstantTypers() {
        super(Instant.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IFormatter.typerIndex:
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public String format(Instant object, IOptions options) {
        return object.toString();
    }

    @Override
    public Instant parse(String text, IOptions options)
            throws ParseException {
        try {
            return Instant.parse(text);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Instant newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        int before = prng.nextInt();
        return fromTheYear(-before);
    }

}

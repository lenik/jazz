package net.bodz.bas.c.java.time;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class OffsetDateTimeTypers
        extends TemporalCommonTypers<OffsetDateTime> {

    public OffsetDateTimeTypers() {
        super(OffsetDateTime.class);
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
    public String format(OffsetDateTime object, IOptions options) {
        return object.toString();
    }

    @Override
    public OffsetDateTime parse(String text, IOptions options)
            throws ParseException {
        try {
            return OffsetDateTime.parse(text);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public OffsetDateTime newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        int before = prng.nextInt();
        Instant sampleInstant = fromTheYear(-before);
        ZoneOffset zoneOffset = getRandomZoneOffset(prng);
        return OffsetDateTime.ofInstant(sampleInstant, zoneOffset);
    }

}

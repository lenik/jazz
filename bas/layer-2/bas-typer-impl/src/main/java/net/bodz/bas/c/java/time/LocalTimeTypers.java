package net.bodz.bas.c.java.time;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class LocalTimeTypers
        extends TemporalCommonTypers<LocalTime> {

    public LocalTimeTypers() {
        super(LocalTime.class);
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
    public String format(LocalTime object, IOptions options) {
        return object.toString();
    }

    @Override
    public LocalTime parse(String text, IOptions options)
            throws ParseException {
        try {
            return LocalTime.parse(text);
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public LocalTime newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        int before = prng.nextInt();
        Instant sampleInstant = fromTheYear(-before);
        ZoneId zoneId = getRandomZoneId(prng);
        // return LocalTime.ofInstant(sampleInstant, zoneId);
        return sampleInstant.atZone(zoneId).toLocalTime();
    }

}

package net.bodz.bas.c.java.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class DateTypers
        extends AbstractCommonTypers<Date> {

    public DateTypers() {
        super(Date.class);
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
    public String format(Date object, IOptions options) {
        DateTimeFormatter dateFormat = options.get(DateTimeFormatter.class, DateTimes.ISO8601);
        return dateFormat.format(DateTimes._convert(object));
    }

    @Override
    public Date parse(String text, IOptions options)
            throws ParseException {
        DateTimeFormatter format = options.get(DateTimeFormatter.class, //
                text.contains(":") ? DateTimes.ISO8601 : DateTimes.ISO_LOCAL_DATE);
        try {
            TemporalAccessor temporal = format.parse(text);
            Instant instant = Instant.from(temporal);
            return new Date(instant.toEpochMilli());
        } catch (DateTimeParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Date newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        int before = prng.nextInt();
        long yearStart = DateTimes.startOfYear().toInstant().toEpochMilli();
        long sample = yearStart - before;
        return new Date(sample);
    }

}

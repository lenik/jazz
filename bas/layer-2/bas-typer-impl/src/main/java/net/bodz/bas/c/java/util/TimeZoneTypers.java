package net.bodz.bas.c.java.util;

import java.util.Random;
import java.util.TimeZone;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class TimeZoneTypers
        extends AbstractCommonTypers<TimeZone> {

    public TimeZoneTypers() {
        super(TimeZone.class);
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
    public String format(TimeZone object, IOptions options) {
        return object.getID();
    }

    @Override
    public TimeZone parse(String text, IOptions options)
            throws ParseException {
        String id = text;
        TimeZone timeZone = TimeZone.getTimeZone(id);
        if (timeZone == null)
            throw new ParseException("Bad time zone id: " + id);
        else
            return timeZone;
    }

    @Override
    public TimeZone newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        String[] ids = TimeZone.getAvailableIDs();
        int index = prng.nextInt(ids.length);
        String id = ids[index];
        return TimeZone.getTimeZone(id);
    }

}

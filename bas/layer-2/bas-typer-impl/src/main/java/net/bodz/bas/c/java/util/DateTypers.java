package net.bodz.bas.c.java.util;

import java.text.DateFormat;
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
        DateFormat dateFormat = options.get(DateFormat.class, Dates.D10T8);
        return dateFormat.format(object);
    }

    @Override
    public Date parse(String text, IOptions options)
            throws ParseException {
        DateFormat format = options.get(DateFormat.class, //
                text.contains(":") ? Dates.D10T8 : Dates.YYYY_MM_DD);
        try {
            return format.parse(text);
        } catch (java.text.ParseException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public Date newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);
        int before = prng.nextInt();
        long sample = System.currentTimeMillis() - before;
        return new Date(sample);
    }

}

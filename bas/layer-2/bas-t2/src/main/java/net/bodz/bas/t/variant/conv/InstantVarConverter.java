package net.bodz.bas.t.variant.conv;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class InstantVarConverter
        extends AbstractVarConverter<Instant> {

    DateTimeFormatter formatter;

    public InstantVarConverter(DateTimeFormatter format) {
        super(Instant.class);
        this.formatter = format;
    }

    @Override
    public Instant fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long instant = in.longValue();
        return Instant.ofEpochMilli(instant);
    }

    @Override
    public Instant fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        if (formatter == null)
            try {
                return VariantConvertContexts.getContext().parseInstant(in);
            } catch (ParseException e) {
                throw new TypeConvertException("Failed to parse date " + in, e);
            }
        else {
            try {
                return Instant.parse(in);
            } catch (DateTimeParseException e) {
                throw new TypeConvertException("Failed to parse date " + in, e);
            }
        }
    }

    @Override
    public String toString(Instant value) {
        if (value == null)
            return null;
        if (formatter == null) {
            return value.toString();
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Number toNumber(Instant value) {
        if (value == null)
            return null;
        return value.toEpochMilli();
    }

    @Override
    public boolean toBoolean(Instant value) {
        if (value == null)
            return false;
        return ! (value.getEpochSecond() == 0 && value.getNano() == 0);
    }

    public static final InstantVarConverter INSTANCE = new InstantVarConverter(null);

}

package net.bodz.bas.t.variant.conv;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class OffsetTimeVarConverter
        extends AbstractVarConverter<OffsetTime> {

    DateTimeFormatter formatter;

    public OffsetTimeVarConverter(DateTimeFormatter format) {
        super(OffsetTime.class);
        this.formatter = format;
    }

    @Override
    public OffsetTime fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return OffsetTime.ofInstant(instant, ZoneOffset.systemDefault());
    }

    @Override
    public OffsetTime fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        try {
            if (formatter == null) {
                IVariantConvertContext context = VariantConvertContexts.getContext();
                return context.parseOffsetTime(in);
            } else {
                return OffsetTime.parse(in, formatter);
            }
        } catch (Exception e) {
            throw new TypeConvertException("Failed to parse offset time " + in, e);
        }
    }

    @Override
    public String toString(OffsetTime value) {
        if (value == null)
            return null;
        if (formatter == null) {
            IVariantConvertContext context = VariantConvertContexts.getContext();
            return context.formatOffsetTime(value);
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Long toNumber(OffsetTime value) {
        if (value == null)
            return null;
        LocalTime timePart = value.toLocalTime();
        long secondOfDay = timePart.toSecondOfDay();
        return secondOfDay;
    }

    @Override
    public boolean toBoolean(OffsetTime value) {
        if (value == null)
            return false;
        LocalTime timePart = value.toLocalTime();
        long nanoOfDay = timePart.toNanoOfDay();
        return nanoOfDay != 0;
    }

    @Override
    public Instant toInstant(OffsetTime value) {
        return Instant.from(value);
    }

    @Override
    public LocalDateTime toLocalDateTime(OffsetTime value) {
        return value.toLocalTime().atDate(LocalDate.now());
    }

    @Override
    public LocalDate toLocalDate(OffsetTime value) {
        return LocalDate.now();
    }

    @Override
    public LocalTime toLocalTime(OffsetTime value) {
        return value.toLocalTime();
    }

    @Override
    public OffsetDateTime toOffsetDateTime(OffsetTime value) {
        return value.atDate(LocalDate.now());
    }

    @Override
    public OffsetTime toOffsetTime(OffsetTime value) {
        return value;
    }

    @Override
    public ZonedDateTime toZonedDateTime(OffsetTime value) {
        return value.atDate(LocalDate.now()).toZonedDateTime();
    }

    public static final OffsetTimeVarConverter INSTANCE = new OffsetTimeVarConverter(null);

}

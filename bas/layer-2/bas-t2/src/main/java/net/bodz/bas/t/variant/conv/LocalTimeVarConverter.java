package net.bodz.bas.t.variant.conv;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class LocalTimeVarConverter
        extends AbstractVarConverter<LocalTime> {

    DateTimeFormatter formatter;

    public LocalTimeVarConverter(DateTimeFormatter format) {
        super(LocalTime.class);
        this.formatter = format;
    }

    @Override
    public LocalTime fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        double secondOfDay = in.doubleValue();
        long nanoOfDay = (long) (secondOfDay * 1e9);
        return LocalTime.ofNanoOfDay(nanoOfDay);
    }

    @Override
    public LocalTime fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        try {
            if (formatter == null) {
                IVariantConvertContext context = VariantConvertContexts.getContext();
                return context.parseLocalTime(in);
            } else {
                return LocalTime.parse(in, formatter);
            }
        } catch (Exception e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(LocalTime value) {
        if (value == null)
            return null;
        if (formatter == null) {
            IVariantConvertContext context = VariantConvertContexts.getContext();
            return context.formatLocalTime(value);
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Double toNumber(LocalTime value) {
        if (value == null)
            return null;
        return value.toNanoOfDay() / 1e9;
    }

    @Override
    public boolean toBoolean(LocalTime value) {
        if (value == null)
            return false;
        return value.toNanoOfDay() != 0;
    }

    @Override
    public Instant toInstant(LocalTime value) {
        LocalDateTime localDateTime = toLocalDateTime(value);
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return zdt.toInstant();
    }

    @Override
    public LocalDateTime toLocalDateTime(LocalTime value) {
        return value.atDate(LocalDate.now());
    }

    @Override
    public LocalDate toLocalDate(LocalTime value) {
        return LocalDate.now();
    }

    @Override
    public LocalTime toLocalTime(LocalTime value) {
        return value;
    }

    @Override
    public OffsetDateTime toOffsetDateTime(LocalTime value) {
        return toZonedDateTime(value).toOffsetDateTime();
    }

    @Override
    public OffsetTime toOffsetTime(LocalTime value) {
        return toOffsetDateTime(value).toOffsetTime();
    }

    @Override
    public ZonedDateTime toZonedDateTime(LocalTime value) {
        return toLocalDateTime(value).atZone(ZoneId.systemDefault());
    }

    public static final LocalTimeVarConverter INSTANCE = new LocalTimeVarConverter(null);

}

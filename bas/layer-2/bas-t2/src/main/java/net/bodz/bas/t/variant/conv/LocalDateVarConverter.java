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

public class LocalDateVarConverter
        extends AbstractVarConverter<LocalDate> {

    DateTimeFormatter formatter;

    public LocalDateVarConverter(DateTimeFormatter format) {
        super(LocalDate.class);
        this.formatter = format;
    }

    @Override
    public LocalDate fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochDay = in.longValue();
        return LocalDate.ofEpochDay(epochDay);
    }

    @Override
    public LocalDate fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        try {
            if (formatter == null) {
                IVariantConvertContext context = VariantConvertContexts.getContext();
                return context.parseLocalDate(in);
            } else {
                return LocalDate.parse(in, formatter);
            }
        } catch (Exception e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(LocalDate value) {
        if (value == null)
            return null;
        if (formatter == null) {
            IVariantConvertContext context = VariantConvertContexts.getContext();
            return context.formatLocalDate(value);
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Integer toNumber(LocalDate value) {
        if (value == null)
            return null;
        return (int) value.toEpochDay();
    }

    @Override
    public boolean toBoolean(LocalDate value) {
        if (value == null)
            return false;
        return value.toEpochDay() != 0;
    }

    @Override
    public Instant toInstant(LocalDate value) {
        return toZonedDateTime(value).toInstant();
    }

    @Override
    public LocalDateTime toLocalDateTime(LocalDate value) {
        return value.atStartOfDay();
    }

    @Override
    public LocalDate toLocalDate(LocalDate value) {
        return value;
    }

    @Override
    public LocalTime toLocalTime(LocalDate value) {
        return LocalTime.MIN;
    }

    @Override
    public OffsetDateTime toOffsetDateTime(LocalDate value) {
        return toZonedDateTime(value).toOffsetDateTime();
    }

    @Override
    public OffsetTime toOffsetTime(LocalDate value) {
        return toOffsetDateTime(value).toOffsetTime();
    }

    @Override
    public ZonedDateTime toZonedDateTime(LocalDate value) {
        return value.atStartOfDay().atZone(ZoneId.systemDefault());
    }

    public static final LocalDateVarConverter INSTANCE = new LocalDateVarConverter(null);

}

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

public class OffsetDateTimeVarConverter
        extends AbstractVarConverter<OffsetDateTime> {

    DateTimeFormatter formatter;

    public OffsetDateTimeVarConverter(DateTimeFormatter format) {
        super(OffsetDateTime.class);
        this.formatter = format;
    }

    @Override
    public OffsetDateTime fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return OffsetDateTime.ofInstant(instant, ZoneOffset.systemDefault());
    }

    @Override
    public OffsetDateTime fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        try {
            if (formatter == null) {
                IVariantConvertContext context = VariantConvertContexts.getContext();
                return context.parseOffsetDateTime(in);
            } else {
                return OffsetDateTime.parse(in, formatter);
            }
        } catch (Exception e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(OffsetDateTime value) {
        if (value == null)
            return null;
        if (formatter == null) {
            IVariantConvertContext context = VariantConvertContexts.getContext();
            return context.formatOffsetDateTime(value);
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Long toNumber(OffsetDateTime value) {
        if (value == null)
            return null;
        return value.toInstant().toEpochMilli();
    }

    @Override
    public boolean toBoolean(OffsetDateTime value) {
        if (value == null)
            return false;
        Instant instant = value.toInstant();
        return ! (instant.toEpochMilli() == 0 && instant.getNano() == 0);
    }

    @Override
    public Instant toInstant(OffsetDateTime value) {
        return value.toInstant();
    }

    @Override
    public LocalDateTime toLocalDateTime(OffsetDateTime value) {
        return value.toLocalDateTime();
    }

    @Override
    public LocalDate toLocalDate(OffsetDateTime value) {
        return value.toLocalDate();
    }

    @Override
    public LocalTime toLocalTime(OffsetDateTime value) {
        return value.toLocalTime();
    }

    @Override
    public OffsetDateTime toOffsetDateTime(OffsetDateTime value) {
        return value;
    }

    @Override
    public OffsetTime toOffsetTime(OffsetDateTime value) {
        return value.toOffsetTime();
    }

    @Override
    public ZonedDateTime toZonedDateTime(OffsetDateTime value) {
        return value.toZonedDateTime();
    }

    public static final OffsetDateTimeVarConverter INSTANCE = new OffsetDateTimeVarConverter(null);

}

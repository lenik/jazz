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

public class ZonedDateTimeVarConverter
        extends AbstractVarConverter<ZonedDateTime> {

    DateTimeFormatter formatter;

    public ZonedDateTimeVarConverter(DateTimeFormatter format) {
        super(ZonedDateTime.class);
        this.formatter = format;
    }

    @Override
    public ZonedDateTime fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public ZonedDateTime fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        try {
            if (formatter == null) {
                IVariantConvertContext context = VariantConvertContexts.getContext();
                return context.parseZonedDateTime(in);
            } else {
                return ZonedDateTime.parse(in, formatter);
            }
        } catch (Exception e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(ZonedDateTime value) {
        if (value == null)
            return null;
        if (formatter == null) {
            IVariantConvertContext context = VariantConvertContexts.getContext();
            return context.formatZonedDateTime(value);
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Long toNumber(ZonedDateTime value) {
        if (value == null)
            return null;
        return value.toInstant().toEpochMilli();
    }

    @Override
    public boolean toBoolean(ZonedDateTime value) {
        if (value == null)
            return false;
        Instant instant = value.toInstant();
        return ! (instant.toEpochMilli() == 0 && instant.getNano() == 0);
    }

    @Override
    public Instant toInstant(ZonedDateTime value) {
        return value.toInstant();
    }

    @Override
    public LocalDateTime toLocalDateTime(ZonedDateTime value) {
        return value.toLocalDateTime();
    }

    @Override
    public LocalDate toLocalDate(ZonedDateTime value) {
        return value.toLocalDate();
    }

    @Override
    public LocalTime toLocalTime(ZonedDateTime value) {
        return value.toLocalTime();
    }

    @Override
    public OffsetDateTime toOffsetDateTime(ZonedDateTime value) {
        return value.toOffsetDateTime();
    }

    @Override
    public OffsetTime toOffsetTime(ZonedDateTime value) {
        return value.toOffsetDateTime().toOffsetTime();
    }

    @Override
    public ZonedDateTime toZonedDateTime(ZonedDateTime value) {
        return value;
    }

    public static final ZonedDateTimeVarConverter INSTANCE = new ZonedDateTimeVarConverter(null);

}

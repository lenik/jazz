package net.bodz.bas.t.variant.conv;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.IVariantConvertContext;
import net.bodz.bas.t.variant.VariantConvertContexts;

public class LocalDateTimeVarConverter
        extends AbstractVarConverter<LocalDateTime> {

    DateTimeFormatter formatter;

    public LocalDateTimeVarConverter(DateTimeFormatter format) {
        super(LocalDateTime.class);
        this.formatter = format;
    }

    @Override
    public LocalDateTime fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        long epochMilli = in.longValue();
        Instant instant = Instant.ofEpochMilli(epochMilli);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }

    @Override
    public LocalDateTime fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        try {
            if (formatter == null) {
                IVariantConvertContext context = VariantConvertContexts.getContext();
                return context.parseLocalDateTime(in);
            } else {
                return LocalDateTime.parse(in, formatter);
            }
        } catch (Exception e) {
            throw new TypeConvertException("Failed to parse date " + in, e);
        }
    }

    @Override
    public String toString(LocalDateTime value) {
        if (value == null)
            return null;
        if (formatter == null) {
            IVariantConvertContext context = VariantConvertContexts.getContext();
            return context.formatLocalDateTime(value);
        } else {
            return formatter.format(value);
        }
    }

    @Override
    public Long toNumber(LocalDateTime value) {
        if (value == null)
            return null;
        return value.toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    @Override
    public boolean toBoolean(LocalDateTime value) {
        if (value == null)
            return false;
        return value.toEpochSecond(ZoneOffset.UTC) != 0;
    }

    @Override
    public Instant toInstant(LocalDateTime value) {
        return toZonedDateTime(value).toInstant();
    }

    @Override
    public LocalDateTime toLocalDateTime(LocalDateTime value) {
        return value;
    }

    @Override
    public LocalDate toLocalDate(LocalDateTime value) {
        return value.toLocalDate();
    }

    @Override
    public LocalTime toLocalTime(LocalDateTime value) {
        return value.toLocalTime();
    }

    @Override
    public OffsetDateTime toOffsetDateTime(LocalDateTime value) {
        return toZonedDateTime(value).toOffsetDateTime();
    }

    @Override
    public OffsetTime toOffsetTime(LocalDateTime value) {
        return toOffsetDateTime(value).toOffsetTime();
    }

    @Override
    public ZonedDateTime toZonedDateTime(LocalDateTime value) {
        return value.atZone(ZoneId.systemDefault());
    }

    public static final LocalDateTimeVarConverter INSTANCE = new LocalDateTimeVarConverter(null);

}

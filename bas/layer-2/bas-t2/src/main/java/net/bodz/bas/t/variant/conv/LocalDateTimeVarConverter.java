package net.bodz.bas.t.variant.conv;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

    public static final LocalDateTimeVarConverter INSTANCE = new LocalDateTimeVarConverter(null);

}

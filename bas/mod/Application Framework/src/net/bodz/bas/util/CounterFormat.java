package net.bodz.bas.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import net.bodz.bas.codec._TextCodec;
import net.bodz.bas.lang.err.DecodeException;
import net.bodz.bas.lang.err.EncodeException;

public class CounterFormat extends _TextCodec<Long> {

    private final NumberFormat format;

    public CounterFormat(String pattern) {
        this.format = new DecimalFormat(pattern);
    }

    @Override
    public Class<? extends Long> getDecodedType() {
        return Long.class;
    }

    @Override
    public String encode(Long value) throws EncodeException {
        return format.format(value);
    }

    @Override
    public Long decode(String encoded) throws DecodeException {
        try {
            return format.parse(encoded).longValue();
        } catch (ParseException e) {
            throw new DecodeException(e);
        }
    }

}

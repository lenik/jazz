package net.bodz.bas.t.variant;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.StringVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

public class VarConv {

    static final IVarConverter<String> STRING = StringVarConverter.INSTANCE;

    public static <T> T fromString(Class<T> type, String str)
            throws ParseException {
        try {
            return STRING.to(str, type);
        } catch (TypeConvertException e) {
            throw new ParseException("failed to parse: " + e.getMessage(), e);
        }
    }

    public static <T> T toAny(Object source, Class<T> type)
            throws ParseException {
        IVarConverter<T> converter = VarConverters.getConverter(type);
        try {
            T val = converter.from(source);
            return val;
        } catch (TypeConvertException e) {
            throw new ParseException("failed to convert: " + e.getMessage(), e);
        }
    }

}

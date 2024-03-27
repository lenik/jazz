package net.bodz.lily.entity;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

public class StrVar {

    static IVarConverter<String> strConv = VarConverters.getConverter(String.class);

    public static <K> K parse(Class<K> type, String s)
            throws ParseException {
        try {
            return strConv.to(s, type);
        } catch (TypeConvertException e) {
            throw new ParseException("failed to parse: " + e.getMessage(), e);
        }
    }

    public static <K> K parseAny(Class<K> type, Object anyVal)
            throws ParseException {
        IVarConverter<K> converter = VarConverters.getConverter(type);
        try {
            K id = converter.from(anyVal);
            return id;
        } catch (TypeConvertException e) {
            throw new ParseException("failed to convert: " + e.getMessage(), e);
        }
    }

}

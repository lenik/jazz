package net.bodz.bas.t.variant.conv;

import net.bodz.bas.err.FormatterException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;

public class TextFormVarConverter
        extends AbstractVarConverterExtension<String> {

    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public boolean canConvertFrom(Class<?> type) {
        IFormatter<?> formatter = Typers.getTyper(type, IFormatter.class);
        if (formatter != null)
            return true;
        return false;
    }

    @Override
    public boolean canConvertTo(Class<?> type) {
        IParser<?> parser = Typers.getTyper(type, IParser.class);
        if (parser != null)
            return true;
        return false;
    }

    @Override
    public String convertFrom(Class<?> type, Object obj)
            throws TypeConvertException {
        IFormatter<Object> formatter = Typers.getTyper(type, IFormatter.class);
        if (formatter == null)
            throw new IllegalArgumentException("No formatter for " + type);
        String str;
        try {
            str = formatter.format(obj);
        } catch (FormatterException e) {
            throw new TypeConvertException("Failed to format: " + e.getMessage(), e);
        }
        return str;
    }

    @Override
    public <U> U convertTo(String str, Class<U> type)
            throws TypeConvertException {
        IParser<U> parser = Typers.getTyper(type, IParser.class);
        if (parser == null)
            throw new IllegalArgumentException("No parser for " + type);
        U obj;
        try {
            obj = parser.parse(str);
        } catch (ParseException e) {
            throw new TypeConvertException("Failed to parse: " + e.getMessage(), e);
        }
        return obj;
    }

}

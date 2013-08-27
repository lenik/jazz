package net.bodz.bas.typer.spi.extra;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.AbstractParser;
import net.bodz.bas.typer.std.IParser;

public class ArrayParser
        extends AbstractParser<Object> {

    private Class<?> valtype;
    private IParser<?> valparser;
    private Pattern separator;

    public ArrayParser(Class<?> valtype, IParser<?> valparser, Pattern separator) {
        if (valparser == null)
            throw new NullPointerException("null valparser");
        this.valtype = valtype;
        this.valparser = valparser;
        this.separator = separator;
    }

    public ArrayParser(Class<?> valtype, Pattern separator)
            throws CreateException {
        // XXX - Call to Typers.getTypers() will definitely make a dead loop.
        this(valtype, Typers.getTyper(valtype, IParser.class), separator);
    }

    public ArrayParser(Class<?> valtype, String separator)
            throws CreateException {
        this(valtype, Pattern.compile(separator));
    }

    @Override
    public Object parse(String s, IOptions options)
            throws ParseException {
        if (s == null)
            return null;
        String[] vals = separator.split(s);
        Object array = Array.newInstance(valtype, vals.length);
        for (int i = 0; i < vals.length; i++) {
            Object val = valparser.parse(vals[i]);
            Array.set(array, i, val);
        }
        return array;
    }

}

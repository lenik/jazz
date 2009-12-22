package net.bodz.bas.commons.typeparser;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.typeinfo.Parser;
import net.bodz.bas.typeinfo.TinyParser;

public class ArrayParser
        extends TinyParser<Object> {

    private Class<?> valtype;
    private Parser<?> valparser;
    private Pattern separator;

    public ArrayParser(Class<?> valtype, Parser<?> valparser, Pattern separator) {
        if (valparser == null)
            throw new NullPointerException("null valparser"); //$NON-NLS-1$
        this.valtype = valtype;
        this.valparser = valparser;
        this.separator = separator;
    }

    public ArrayParser(Class<?> valtype, Pattern separator)
            throws CreateException {
        this(valtype, TypeParsers.guess(valtype), separator);
    }

    public ArrayParser(Class<?> valtype, String separator)
            throws CreateException {
        this(valtype, Pattern.compile(separator));
    }

    @Override
    public Object parse(String s)
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

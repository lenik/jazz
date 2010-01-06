package net.bodz.bas.type.parser;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

import net.bodz.bas.exceptions.CreateException;
import net.bodz.bas.exceptions.ParseException;
import net.bodz.bas.type.traits.IParser;
import net.bodz.bas.type.traits.impl.AbstractParser;
import net.bodz.bas.type.traits.impl.ParserResolve;

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
        this(valtype, ParserResolve.guess(valtype), separator);
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

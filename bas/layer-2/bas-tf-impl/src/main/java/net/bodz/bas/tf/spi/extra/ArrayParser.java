package net.bodz.bas.tf.spi.extra;

import java.lang.reflect.Array;
import java.util.regex.Pattern;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.tf.TypeFeatures;
import net.bodz.bas.tf.std.AbstractParser;
import net.bodz.bas.tf.std.IParser;
import net.bodz.bas.rtx.IOptions;

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
        // XXX - Call to TypeFeatures.getTypeFeatures() will definitely make a dead loop.
        this(valtype, TypeFeatures.getTypeFeature(valtype, IParser.class), separator);
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

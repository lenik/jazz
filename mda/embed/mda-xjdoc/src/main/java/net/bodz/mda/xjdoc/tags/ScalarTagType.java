package net.bodz.mda.xjdoc.tags;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public abstract class ScalarTagType<T>
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation)
            throws ParseException {
        return parse(string);
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation)
            throws FormatException {
        if (value == null)
            return new String[0];
        else {
            @SuppressWarnings("unchecked")
            String s = format((T) value);
            return new String[] { s };
        }
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        T value = parse(string);
        return value;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        @SuppressWarnings("unchecked")
        String string = format((T) value);
        out.attribute(prefix, string);
    }

    protected abstract T parse(String s)
            throws ParseException;

    protected abstract String format(T value)
            throws FormatException;

}

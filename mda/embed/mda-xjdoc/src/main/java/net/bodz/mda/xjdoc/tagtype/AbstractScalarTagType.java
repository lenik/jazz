package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public abstract class AbstractScalarTagType<T>
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        return parse(string);
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        if (value != null) {
            @SuppressWarnings("unchecked")
            String s = format((T) value);
            writer.writeTag(rootTagName, s);
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

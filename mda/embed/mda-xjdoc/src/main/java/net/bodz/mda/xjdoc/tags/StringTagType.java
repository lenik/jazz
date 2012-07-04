package net.bodz.mda.xjdoc.tags;

import java.io.IOException;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class StringTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation) {
        return string;
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation) {
        if (value == null)
            return new String[0];
        else
            return new String[] { value.toString() };
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation) {
        return string;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws IOException {
        String string = (String) value;
        out.attribute(prefix, string);
    }

    public static final StringTagType INSTANCE = new StringTagType();

}

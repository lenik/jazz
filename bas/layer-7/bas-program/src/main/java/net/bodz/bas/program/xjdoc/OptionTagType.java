package net.bodz.bas.program.xjdoc;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.tagtype.AbstractTagType;
import net.bodz.mda.xjdoc.tagtype.IJavadocWriter;

public class OptionTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        return null;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, INegotiation negotiation)
            throws FormatException, IOException {
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        return null;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException {
    }

}

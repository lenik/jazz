package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class StringTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        // TODO normalize-space?
        return string;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        String string = (String) value;
        // TODO Line-wrap...
        writer.writeTag(rootTagName, string);
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

    static final StringTagType INSTANCE = new StringTagType();

    public static StringTagType getInstance() {
        return INSTANCE;
    }

}

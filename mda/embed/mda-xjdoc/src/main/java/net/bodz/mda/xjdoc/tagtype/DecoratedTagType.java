package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.t.model.AbstractDecorator;
import net.bodz.bas.text.flatf.IFlatfOutput;

public abstract class DecoratedTagType
        extends AbstractDecorator<ITagType>
        implements ITagType {

    private static final long serialVersionUID = 1L;

    public DecoratedTagType(ITagType _orig) {
        super(_orig);
    }

    @Override
    public Object parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {
        return _orig.parseJavadoc(tagNameSpec, cont, string, negotiation);
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        _orig.writeJavadoc(rootTagName, writer, value, negotiation);
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        return _orig.parseEntry(cont, suffix, string, negotiation);
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws FormatException, IOException {
        _orig.writeEntries(out, prefix, value, negotiation);
    }

}

package net.bodz.bas.program.xjdoc;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.text.flatf.IFlatfOutput;
import net.bodz.mda.xjdoc.tagtype.AbstractTagType;
import net.bodz.mda.xjdoc.tagtype.IJavadocWriter;

public class OptionTagType
        extends AbstractTagType {

    @Override
    public Object parseJavadoc(String tagNameSpec, Object cont, String string, IOptions options)
            throws ParseException {
        return null;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, IOptions options)
            throws IOException {
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, IOptions options)
            throws ParseException {
        return null;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException {
    }

}

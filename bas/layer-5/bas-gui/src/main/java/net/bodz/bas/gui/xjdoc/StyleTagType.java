package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.tagtype.AbstractScalarTagType;

public class StyleTagType
        extends AbstractScalarTagType<String> {

    @Override
    protected String parse(String s)
            throws ParseException {
        return null;
    }

    @Override
    protected String format(String value)
            throws FormatException {
        return null;
    }

}

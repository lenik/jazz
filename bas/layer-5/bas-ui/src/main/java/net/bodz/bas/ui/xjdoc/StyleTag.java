package net.bodz.bas.ui.xjdoc;

import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.tagtype.SimpleDocTag;

public class StyleTag
        extends SimpleDocTag<TextMap<String>> {

    @Override
    protected TextMap<String> parse(String s)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    protected String format(TextMap<String> value) {
        throw new NotImplementedException();
    }

}

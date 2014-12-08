package net.bodz.bas.ui.xjdoc;

import net.bodz.bas.c.java.util.TextMap;
import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.mda.xjdoc.tagtype.AbstractScalarTagType;

public class StyleTagType
        extends AbstractScalarTagType<TextMap<String>> {

    @Override
    public Class<?> getValueType() {
        return TextMap.class;
    }

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

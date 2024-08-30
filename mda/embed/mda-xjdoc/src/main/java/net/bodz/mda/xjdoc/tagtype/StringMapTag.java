package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.t.coll.StringMapContainer;

public class StringMapTag
        extends AbstractMapDocTag<String> {

    public StringMapTag() {
        super(new StringMapContainer<String>());
    }

    @Override
    protected String parseJavadoc(String s)
            throws ParseException {
        return s;
    }

    @Override
    protected String formatJavadoc(String value) {
        return value;
    }

    @Override
    protected String parseFlatf(String s)
            throws ParseException {
        return s;
    }

    @Override
    protected String formatFlatf(IFlatfOutput out, String value) {
        return value;
    }

}

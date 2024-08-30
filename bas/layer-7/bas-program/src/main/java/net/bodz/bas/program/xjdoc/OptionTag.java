package net.bodz.bas.program.xjdoc;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.program.model.IOption;
import net.bodz.mda.xjdoc.tagtype.SimpleDocTag;

public class OptionTag
        extends SimpleDocTag<IOption> {

    @Override
    protected IOption parse(String s)
            throws ParseException {
        return null;
    }

    @Override
    protected String format(IOption value) {
        return null;
    }

}

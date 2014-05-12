package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;

public class PlainStringTypers
        extends iStringTypers {

    @Override
    public iString parse(String text)
            throws ParseException {
        return iString.fn.val(text);
    }

    @Override
    public iString parse(String text, IOptions options)
            throws ParseException {
        return iString.fn.val(text);
    }

    @Override
    public String format(iString object) {
        return object.toPlainText();
    }

    @Override
    public String format(iString object, IOptions options) {
        return object.toPlainText();
    }

    public static final PlainStringTypers INSTANCE = new PlainStringTypers();

}

package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;

public class ParaLangStringTypers
        extends iStringTypers {

    @Override
    public iString parse(String text)
            throws ParseException {
        return ParaLangStrings.parse(text);
    }

    @Override
    public iString parse(String text, IOptions options)
            throws ParseException {
        return parse(text);
    }

    @Override
    public String format(iString object) {
        return ParaLangStrings.format(object);
    }

    @Override
    public String format(iString object, IOptions options) {
        String separator = options.get(OPT_LANG_SEPARATOR, defaultLangSeparator);
        return ParaLangStrings.format(object, separator);
    }

    public static final ParaLangStringTypers INSTANCE = new ParaLangStringTypers();

}

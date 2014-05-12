package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;

public class MultiLangStringTypers
        extends iStringTypers {

    @ParameterType(String.class)
    public static final String OPT_LINE_SEPARATOR = "lineSeparator";
    public static final String defaultLineSeparator = null;

    @Override
    public iString parse(String text)
            throws ParseException {
        return MultiLangStrings.parse(text);
    }

    @Override
    public iString parse(String text, IOptions options)
            throws ParseException {
        return parse(text);
    }

    @Override
    public String format(iString object) {
        return format(object, IOptions.NULL);
    }

    @Override
    public String format(iString object, IOptions options) {
        String langSeparator = options.get(OPT_LANG_SEPARATOR, defaultLangSeparator);
        String lineSeparator = options.get(OPT_LINE_SEPARATOR, defaultLineSeparator);
        return MultiLangStrings.format(object, langSeparator, lineSeparator);
    }

    public static final MultiLangStringTypers INSTANCE = new MultiLangStringTypers();

}

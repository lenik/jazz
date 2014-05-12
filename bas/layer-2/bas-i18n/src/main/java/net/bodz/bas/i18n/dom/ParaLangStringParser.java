package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.IParser;

public class ParaLangStringParser
        implements IParser<iString> {

    @Override
    public iString parse(String s)
            throws ParseException {
        XiString result = new XiString();
        ParaLangStrings.parse(result, s);
        return result;
    }

    @Override
    public iString parse(String s, IOptions options)
            throws ParseException {
        return parse(s);
    }

    private static final ParaLangStringParser instance = new ParaLangStringParser();

    public static ParaLangStringParser getInstance() {
        return instance;
    }

}

package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ITextFormat;

public abstract class AbstractiStringTypers
        extends AbstractCommonTypers<iString> {

    @ParameterType(String.class)
    public static final String OPT_LANG_SEPARATOR = "langSeparator";
    public static final String defaultLangSeparator = "\n";

    public AbstractiStringTypers() {
        super(iString.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case IFormatter.typerIndex:
        case ITextFormat.typerIndex:
            return this;
        }
        return null;
    }

    public static final int PLAIN = 0;
    public static final int MULTI_LANG = 1;
    public static final int PARA_LANG = 2;

    public static AbstractiStringTypers getInstance(int format) {
        switch (format) {
        case PLAIN:
            return PlainStringTypers.INSTANCE;
        case MULTI_LANG:
            return MultiLangStringTypers.INSTANCE;
        case PARA_LANG:
            return ParaLangStringTypers.INSTANCE;
        default:
            throw new NoSuchKeyException(format);
        }
    }

}

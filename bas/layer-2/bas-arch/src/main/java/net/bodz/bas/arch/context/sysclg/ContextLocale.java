package net.bodz.bas.arch.context.sysclg;

import java.util.Locale;

import net.bodz.bas.arch.context.ContextLocal;

public class ContextLocale
        extends ContextLocal<Locale> {

    @Override
    protected Locale getDefault() {
        return Locale.getDefault();
    }

    static final ContextLocale instance = new ContextLocale();

    public static ContextLocale getInstance() {
        return instance;
    }

}

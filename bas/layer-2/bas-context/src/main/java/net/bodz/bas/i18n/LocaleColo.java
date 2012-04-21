package net.bodz.bas.i18n;

import java.util.Locale;

import net.bodz.bas.context.ContextLocal;

public class LocaleColo
        extends ContextLocal<Locale> {

    @Override
    public Locale getRoot() {
        return Locale.getDefault();
    }

    static final LocaleColo instance = new LocaleColo();

    public static LocaleColo getInstance() {
        return instance;
    }

}

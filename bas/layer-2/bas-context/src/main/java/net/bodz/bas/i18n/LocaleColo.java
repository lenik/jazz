package net.bodz.bas.i18n;

import java.util.Locale;

import net.bodz.bas.context.ContextLocal;

public class LocaleColo
        extends ContextLocal<Locale> {

    @Override
    public Locale getRoot() {
        return Locale.getDefault();
    }

    public String getPath() {
        Locale locale = get();
        String tag = locale.toLanguageTag();
        // tag = locale.toString();
        return tag;
    }

    static final LocaleColo instance = new LocaleColo();

    public static LocaleColo getInstance() {
        return instance;
    }

}

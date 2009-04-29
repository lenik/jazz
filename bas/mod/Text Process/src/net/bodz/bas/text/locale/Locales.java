package net.bodz.bas.text.locale;

import java.util.Locale;

public class Locales {

    static final char SEPARATOR = '_';

    public static Locale getLocale(String name) {
        String lang = name;
        int sep = lang.indexOf(SEPARATOR);
        if (sep == -1)
            return new Locale(lang);

        String country = lang.substring(sep + 1);
        lang = lang.substring(0, sep);
        sep = country.indexOf(SEPARATOR);
        if (sep == -1)
            return new Locale(lang, country);

        String variant = country.substring(sep + 1);
        country = country.substring(0, sep);
        return new Locale(lang, country, variant);
    }

}

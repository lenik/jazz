package net.bodz.bas.type.parser;

import java.util.Locale;

import net.bodz.bas.type.traits.AbstractParser;
import net.bodz.bas.util.exception.ParseException;

public class LocaleParser
        extends AbstractParser<Locale> {

    static final char SEPARATOR = '_';

    @Override
    public Locale parse(String localeName)
            throws ParseException {
        return parseLocale(localeName);
    }

    public static Locale parseLocale(String localeName)
            throws ParseException {
        String lang = localeName;
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

package net.bodz.bas.c.java.util;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class LocaleTypers
        extends AbstractCommonTypers<Locale> {

    /**
     * The separator used to join locale components.
     */
    @ParameterType(String.class)
    public static final String textformSeparator = "separator";
    public static final String defaultTextformSeparator = "_";

    public LocaleTypers() {
        super(Locale.class);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public Locale parse(String text, IOptions options) {
        String separator = options.get(textformSeparator, defaultTextformSeparator);
        return parseLocale(text, separator);
    }

    public static Locale parseLocale(String localeName) {
        return parseLocale(localeName, defaultTextformSeparator);
    }

    public static Locale parseLocale(String localeName, String separator) {
        String lang = localeName;
        int sep = lang.indexOf(separator);
        if (sep == -1)
            return new Locale(lang);

        String country = lang.substring(sep + 1);
        lang = lang.substring(0, sep);
        sep = country.indexOf(separator);
        if (sep == -1)
            return new Locale(lang, country);

        String variant = country.substring(sep + 1);
        country = country.substring(0, sep);
        return new Locale(lang, country, variant);
    }

    static WeakReference<Locale[]> availableLocalesRef;
    static {
        availableLocalesRef = new WeakReference<Locale[]>(null);
    }

    @Override
    public synchronized Locale newSample(IOptions options)
            throws CreateException {
        Locale[] availableLocales = availableLocalesRef.get();
        if (availableLocales == null) {
            availableLocales = Locale.getAvailableLocales();
            availableLocalesRef = new WeakReference<Locale[]>(availableLocales);
        }

        Random prng = options.get(Random.class, random);
        int randomIndex = prng.nextInt(availableLocales.length);
        return availableLocales[randomIndex];
    }

}

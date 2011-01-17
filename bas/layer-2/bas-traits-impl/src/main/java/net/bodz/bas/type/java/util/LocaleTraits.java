package net.bodz.bas.type.java.util;

import java.lang.ref.WeakReference;
import java.util.Locale;

import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.bas.lang.negotiation.NegotiationParameter;
import net.bodz.bas.meta.util.ValueType;
import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IParser;
import net.bodz.bas.traits.ISampleGenerator;
import net.bodz.bas.util.exception.CreateException;
import net.bodz.bas.util.exception.ParseException;

public class LocaleTraits
        extends AbstractCommonTraits<Locale> {

    /**
     * The separator used to join locale components.
     */
    @ValueType(String.class)
    public static final String textformSeparator = "separator";
    public static final String defaultTextformSeparator = "_";

    public LocaleTraits() {
        super(Locale.class);
    }

    @Override
    public IParser<Locale> getParser() {
        return this;
    }

    @Override
    public ISampleGenerator<Locale> getSampleGenerator() {
        return this;
    }

    @Override
    public Locale parse(String localeName)
            throws ParseException {
        return parseLocale(localeName, defaultTextformSeparator);
    }

    @Override
    public Locale parse(String text, INegotiation negotiation)
            throws ParseException, NegotiationException {
        String separator = defaultTextformSeparator;

        for (NegotiationParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (textformSeparator.equals(paramId))
                separator = (String) paramValue;
        }

        return parseLocale(text, separator);
    }

    public static Locale parseLocale(String localeName, String separator)
            throws ParseException {
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
    public synchronized Locale newSample()
            throws CreateException {
        Locale[] availableLocales = availableLocalesRef.get();
        if (availableLocales == null) {
            availableLocales = Locale.getAvailableLocales();
            availableLocalesRef = new WeakReference<Locale[]>(availableLocales);
        }
        int randomIndex = random.nextInt(availableLocales.length);
        return availableLocales[randomIndex];
    }

}

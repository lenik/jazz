package net.bodz.bas.c.java.util;

import java.lang.ref.WeakReference;
import java.util.Locale;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ParameterType;
import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IParser;
import net.bodz.bas.mf.std.ISampleGenerator;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.rtx.INegotiation.IParameter;

public class LocaleMdaFeatures
        extends AbstractCommonMdaFeatures<Locale> {

    /**
     * The separator used to join locale components.
     */
    @ParameterType(String.class)
    public static final String textformSeparator = "separator";
    public static final String defaultTextformSeparator = "_";

    public LocaleMdaFeatures() {
        super(Locale.class);
    }

    @Override
    protected Object query(int mdaFeatureIndex) {
        switch (mdaFeatureIndex) {
        case IParser.mdaFeatureIndex:
        case ISampleGenerator.mdaFeatureIndex:
            return this;
        }
        return null;
    }

    @Override
    public Locale parse(String localeName)
            throws ParseException {
        return parseLocale(localeName);
    }

    @Override
    public Locale parse(String text, INegotiation negotiation) {
        String separator = defaultTextformSeparator;

        for (IParameter param : negotiation) {
            String paramId = param.getId();
            Object paramValue = param.getValue();
            if (paramValue == null)
                continue;
            if (textformSeparator.equals(paramId))
                separator = (String) paramValue;
        }

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

package net.bodz.bas.i18n;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatColo
        extends AbstractLocalizedColo<NumberFormat> {

    @Override
    public NumberFormat getRoot() {
        Locale locale = getLocale();
        return NumberFormat.getInstance(locale);
    }

}

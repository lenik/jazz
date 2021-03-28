package net.bodz.bas.i18n.nls;

import java.util.Locale;

public class FoodNlstr
        extends ClassResourceNlstr {

    public FoodNlstr(INlsTranslator next, Locale locale) {
        super(next, locale);
    }

    public FoodNlstr(INlsTranslator next) {
        super(next);
    }

    public static final INlsTranslator FoodNLS = new FoodNlstr(null);

}

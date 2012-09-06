package net.bodz.bas.i18n.nls;

import java.util.Locale;

public class FoodNLS
        extends ClassResourceNLS {

    public FoodNLS(NLS next, Locale locale) {
        super(next, locale);
    }

    public FoodNLS(NLS next) {
        super(next);
    }

    public static final NLS FoodNLS = new FoodNLS(null);

}

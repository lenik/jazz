package net.bodz.bas.i18n.nls;

import java.util.Locale;
import java.util.MissingResourceException;

public class ClassResourceNLS
        extends ResourceBundleNLS {

    public ClassResourceNLS(NLS next, Locale locale)
            throws MissingResourceException {
        super(next, locale);
    }

    public ClassResourceNLS(NLS next)
            throws MissingResourceException {
        super(next);
    }

}

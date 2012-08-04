package net.bodz.swt.nls;

import java.util.Locale;

import net.bodz.bas.i18n.nls.ClassResourceNLS;
import net.bodz.bas.i18n.nls.NLS;

public class ControlsNLS
        extends ClassResourceNLS {

    public ControlsNLS(NLS next, Locale locale) {
        super(next, locale);
    }

    public ControlsNLS(NLS next) {
        super(next);
    }

    public static final ControlsNLS ControlsNLS = new ControlsNLS(null);

}

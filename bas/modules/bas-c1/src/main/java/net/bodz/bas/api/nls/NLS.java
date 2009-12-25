package net.bodz.bas.api.nls;

import java.util.Locale;

public abstract class NLS
        extends VariantLookupMap<String> {

    public abstract Locale getLocale();

}

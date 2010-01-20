package net.bodz.bas.i18n.nls_2;

import java.util.Locale;

import net.bodz.bas.collection.map.VariantLookupMap;

public abstract class NLS
        extends VariantLookupMap<String> {

    public abstract Locale getLocale();

}

package net.bodz.bas.context.ref;

import java.util.Locale;

import net.bodz.bas.t.ref.ReadOnlyRef;

public class DefaultLocaleRef
        extends ReadOnlyRef<Locale> {

    @Override
    public Class<? extends Locale> getValueType() {
        return Locale.class;
    }

    @Override
    public Locale get() {
        return Locale.getDefault();
    }

    public static DefaultLocaleRef INSTANCE = new DefaultLocaleRef();

}

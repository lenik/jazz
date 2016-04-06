package net.bodz.bas.ctx.sys;

import java.util.Locale;

public class DefaultLocaleRef
        extends SystemConstantRef<Locale> {

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

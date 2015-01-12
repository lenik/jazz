package net.bodz.bas.i18n;

import java.text.DateFormat;

public class TimeFormatScr
        extends I18nScopedRef<DateFormat> {

    int style = DateFormat.DEFAULT;

    public TimeFormatScr() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getTimeInstance(style, getLocale());
    }

}

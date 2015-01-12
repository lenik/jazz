package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateFormatScr
        extends I18nScopedRef<DateFormat> {

    int style = DateFormat.DEFAULT;

    public DateFormatScr() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getDateInstance(style, getLocale());
    }

}

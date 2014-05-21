package net.bodz.bas.i18n;

import java.text.DateFormat;

public class TimeFormatCtl
        extends I18nContextLocal<DateFormat> {

    int style = DateFormat.DEFAULT;

    public TimeFormatCtl() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getTimeInstance(style, getLocale());
    }

}

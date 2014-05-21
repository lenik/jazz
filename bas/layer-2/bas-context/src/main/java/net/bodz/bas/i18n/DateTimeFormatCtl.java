package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateTimeFormatCtl
        extends I18nContextLocal<DateFormat> {

    private int dateStyle = DateFormat.DEFAULT;
    private int timeStyle = DateFormat.DEFAULT;

    public DateTimeFormatCtl() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getDateTimeInstance(dateStyle, timeStyle, getLocale());
    }

}

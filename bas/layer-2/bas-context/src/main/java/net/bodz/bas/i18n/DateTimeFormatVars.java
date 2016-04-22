package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateTimeFormatVars
        extends I18nScopedRef<DateFormat> {

    private int dateStyle = DateFormat.DEFAULT;
    private int timeStyle = DateFormat.DEFAULT;

    public DateTimeFormatVars() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getDateTimeInstance(dateStyle, timeStyle, getLocale());
    }

}

package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateFormatVars
        extends I18nScopedRef<DateFormat> {

    int style = DateFormat.DEFAULT;

    public DateFormatVars() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getDateInstance(style, getLocale());
    }

}

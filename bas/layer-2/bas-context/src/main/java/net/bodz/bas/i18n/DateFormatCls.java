package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateFormatCls
        extends I18nContextLocal<DateFormat> {

    int style = DateFormat.DEFAULT;

    public DateFormatCls() {
        super(DateFormat.class);
    }

    @Override
    public DateFormat getDefaultValue() {
        return DateFormat.getDateInstance(style, getLocale());
    }

}

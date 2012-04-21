package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateTimeFormatColo
        extends AbstractLocalizedColo<DateFormat> {

    int dateStyle;
    int timeStyle;

    public DateTimeFormatColo() {
        this(DateFormat.DEFAULT, DateFormat.DEFAULT);
    }

    public DateTimeFormatColo(int dateStyle, int timeStyle) {
        this.dateStyle = dateStyle;
        this.timeStyle = timeStyle;
    }

    @Override
    public DateFormat getRoot() {
        return DateFormat.getDateTimeInstance(dateStyle, timeStyle, getLocale());
    }

}

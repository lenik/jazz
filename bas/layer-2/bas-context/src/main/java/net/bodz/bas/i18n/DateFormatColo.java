package net.bodz.bas.i18n;

import java.text.DateFormat;

public class DateFormatColo
        extends AbstractLocalizedColo<DateFormat> {

    int style;

    public DateFormatColo() {
        this(DateFormat.DEFAULT);
    }

    public DateFormatColo(int style) {
        this.style = style;
    }

    @Override
    public DateFormat getRoot() {
        return DateFormat.getDateInstance(style, getLocale());
    }

}

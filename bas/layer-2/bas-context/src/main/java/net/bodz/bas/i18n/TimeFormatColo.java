package net.bodz.bas.i18n;

import java.text.DateFormat;

public class TimeFormatColo
        extends AbstractLocalizedColo<DateFormat> {

    int style;

    public TimeFormatColo() {
        this(DateFormat.DEFAULT);
    }

    public TimeFormatColo(int style) {
        this.style = style;
    }

    @Override
    public DateFormat getRoot() {
        return DateFormat.getTimeInstance(style, getLocale());
    }

}

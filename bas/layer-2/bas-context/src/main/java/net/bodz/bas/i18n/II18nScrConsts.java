package net.bodz.bas.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;

import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.sugar.IConstants;

public interface II18nScrConsts
        extends IConstants {

    LocaleScr LOCALE = new LocaleScr();
    CharsetScr CHARSET = new CharsetScr();

    ScopedRef<DateFormat> DATE_FORMAT = new DateFormatScr();
    ScopedRef<DateFormat> TIME_FORMAT = new TimeFormatScr();
    ScopedRef<DateFormat> DATETIME_FORMAT = new DateTimeFormatScr();

    ScopedRef<NumberFormat> CURRENTY_FORMAT = new CurrencyFormatScr();
    ScopedRef<NumberFormat> INTEGER_FORMAT = new IntegerFormatScr();
    ScopedRef<NumberFormat> PERCENT_FORMAT = new PercentFormatScr();
    ScopedRef<NumberFormat> NUMBER_FORMAT = new NumberFormatScr();

}

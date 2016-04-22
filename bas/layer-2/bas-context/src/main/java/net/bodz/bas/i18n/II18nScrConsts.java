package net.bodz.bas.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;

import net.bodz.bas.ctx.scope.ScopedRef;
import net.bodz.bas.sugar.IConstants;

public interface II18nScrConsts
        extends IConstants {

    LocaleVars LOCALE = new LocaleVars();
    CharsetVars CHARSET = new CharsetVars();

    ScopedRef<DateFormat> DATE_FORMAT = new DateFormatVars();
    ScopedRef<DateFormat> TIME_FORMAT = new TimeFormatVars();
    ScopedRef<DateFormat> DATETIME_FORMAT = new DateTimeFormatVars();

    ScopedRef<NumberFormat> CURRENTY_FORMAT = new CurrencyFormatVars();
    ScopedRef<NumberFormat> INTEGER_FORMAT = new IntegerFormatVars();
    ScopedRef<NumberFormat> PERCENT_FORMAT = new PercentFormatVars();
    ScopedRef<NumberFormat> NUMBER_FORMAT = new NumberFormatVars();

}

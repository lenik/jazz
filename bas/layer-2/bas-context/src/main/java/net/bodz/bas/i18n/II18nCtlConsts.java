package net.bodz.bas.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;

import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.sugar.IConstants;

public interface II18nCtlConsts
        extends IConstants {

    LocaleCtl LOCALE = new LocaleCtl();
    CharsetCtl CHARSET = new CharsetCtl();

    ContextLocal<DateFormat> DATE_FORMAT = new DateFormatCls();
    ContextLocal<DateFormat> TIME_FORMAT = new TimeFormatCtl();
    ContextLocal<DateFormat> DATETIME_FORMAT = new DateTimeFormatCtl();

    ContextLocal<NumberFormat> CURRENTY_FORMAT = new CurrencyFormatCtl();
    ContextLocal<NumberFormat> INTEGER_FORMAT = new IntegerFormatCtl();
    ContextLocal<NumberFormat> PERCENT_FORMAT = new PercentFormatCtl();
    ContextLocal<NumberFormat> NUMBER_FORMAT = new NumberFormatCtl();

}

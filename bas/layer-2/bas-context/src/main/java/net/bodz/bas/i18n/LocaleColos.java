package net.bodz.bas.i18n;

import java.text.DateFormat;
import java.text.NumberFormat;

import net.bodz.bas.context.ContextLocal;
import net.bodz.bas.context.ContextLocalGroup;

public interface LocaleColos
        extends ContextLocalGroup {

    LocaleColo locale = LocaleColo.getInstance();
    CharsetColo charset = CharsetColo.getInstance();

    ContextLocal<DateFormat> dateFormat = new DateFormatColo();
    ContextLocal<DateFormat> timeFormat = new TimeFormatColo();
    ContextLocal<DateFormat> dateTimeFormat = new DateTimeFormatColo();

    ContextLocal<NumberFormat> currencyFormat = new CurrencyFormatColo();
    ContextLocal<NumberFormat> integerFormat = new IntegerFormatColo();
    ContextLocal<NumberFormat> percentFormat = new PercentFormatColo();
    ContextLocal<NumberFormat> numberFormat = new NumberFormatColo();

}

package net.bodz.bas.repr.path;

import java.time.LocalDate;
import java.time.LocalTime;

import net.bodz.bas.meta.decl.NotNull;

public class DateTimeParseOptions
        implements IDateTimeParseOptions {

    LocalDate defaultDate = LocalDate.now();
    LocalTime defaultTime = LocalTime.of(0, 0);

    @Override
    public LocalDate getDefaultDate() {
        return defaultDate;
    }

    public void setDefaultDate(LocalDate defaultDate) {
        this.defaultDate = defaultDate;
    }

    @Override
    public LocalTime getDefaultTime() {
        return defaultTime;
    }

    public void setDefaultTime(LocalTime defaultTime) {
        this.defaultTime = defaultTime;
    }

    public static DateTimeParseOptions defaultDate(@NotNull LocalDate defaultDate) {
        DateTimeParseOptions options = new DateTimeParseOptions();
        options.defaultDate = defaultDate;
        return options;
    }

    public static DateTimeParseOptions defaultTime(@NotNull LocalTime defaultTime) {
        DateTimeParseOptions options = new DateTimeParseOptions();
        options.defaultTime = defaultTime;
        return options;
    }

}

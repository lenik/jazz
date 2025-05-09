package net.bodz.bas.repr.path;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IDateTimeParseOptions {

    LocalDate getDefaultDate();

    LocalTime getDefaultTime();

}

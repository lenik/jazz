package net.bodz.lily.t.struct;

import java.io.IOException;

import org.joda.time.Period;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;

public class CalendarPeriod
        extends MixinStruct {

    private static final long serialVersionUID = 1L;

    int years;
    int months;
    int weeks;
    int days;
    boolean workDays;
    int hours;
    int minutes;
    int seconds;

    public Period toPeriod() {
        if (workDays)
            throw new UnsupportedOperationException("workDays isn't supported.");
        return new Period(years, months, weeks, days, hours, minutes, seconds, 0);
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        years = o.getInt("years", years);
        months = o.getInt("months", months);
        weeks = o.getInt("weeks", weeks);
        days = o.getInt("days", days);
        workDays = o.getBoolean("workdays", workDays);
        hours = o.getInt("hours", hours);
        minutes = o.getInt("minutes", minutes);
        seconds = o.getInt("seconds", seconds);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("years", years);
        out.entry("months", months);
        out.entry("weeks", weeks);
        out.entry("days", days);
        out.entry("workDays", workDays);
        out.entry("hours", hours);
        out.entry("minutes", minutes);
        out.entry("seconds", seconds);
    }

}

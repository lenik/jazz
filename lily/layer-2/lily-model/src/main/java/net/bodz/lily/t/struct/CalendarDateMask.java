package net.bodz.lily.t.struct;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.t.range.ShortRange;

/**
 * @see CalendarDate
 */
public class CalendarDateMask
        extends MixinStruct {

    ShortRange year = new ShortRange();
    ShortRange month = new ShortRange();
    ShortRange day = new ShortRange();
    ShortRange week = new ShortRange();
    ShortRange weekDay = new ShortRange();
    ShortRange hour = new ShortRange();
    ShortRange minute = new ShortRange();
    ShortRange second = new ShortRange();

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
    }

    public ShortRange getYear() {
        return year;
    }

    public void setYear(ShortRange year) {
        this.year = year;
    }

    public ShortRange getMonth() {
        return month;
    }

    public void setMonth(ShortRange month) {
        this.month = month;
    }

    public ShortRange getDay() {
        return day;
    }

    public void setDay(ShortRange day) {
        this.day = day;
    }

    public ShortRange getWeek() {
        return week;
    }

    public void setWeek(ShortRange week) {
        this.week = week;
    }

    public ShortRange getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(ShortRange weekDay) {
        this.weekDay = weekDay;
    }

    public ShortRange getHour() {
        return hour;
    }

    public void setHour(ShortRange hour) {
        this.hour = hour;
    }

    public ShortRange getMinute() {
        return minute;
    }

    public void setMinute(ShortRange minute) {
        this.minute = minute;
    }

    public ShortRange getSecond() {
        return second;
    }

    public void setSecond(ShortRange second) {
        this.second = second;
    }

}

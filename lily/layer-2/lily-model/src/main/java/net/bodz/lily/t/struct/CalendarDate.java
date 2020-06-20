package net.bodz.lily.t.struct;

import java.io.IOException;

import org.joda.time.LocalDateTime;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;

public class CalendarDate
        extends MixinStruct {

    private static final long serialVersionUID = 1L;

    short year;
    short month;
    short day;
    short week;
    short weekDay;
    short hour;
    short minute;
    short second;

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getMonth() {
        return month;
    }

    public void setMonth(short month) {
        this.month = month;
    }

    public short getDay() {
        return day;
    }

    public void setDay(short day) {
        this.day = day;
    }

    public short getWeek() {
        return week;
    }

    public void setWeek(short week) {
        this.week = week;
    }

    public short getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(short weekDay) {
        this.weekDay = weekDay;
    }

    public short getHour() {
        return hour;
    }

    public void setHour(short hour) {
        this.hour = hour;
    }

    public short getMinute() {
        return minute;
    }

    public void setMinute(short minute) {
        this.minute = minute;
    }

    public short getSecond() {
        return second;
    }

    public void setSecond(short second) {
        this.second = second;
    }

    public LocalDateTime toLocalDateTime() {
        LocalDateTime t = new LocalDateTime(year, month, day, hour, minute, second);
        return t;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        year = o.getShort("year", year);
        month = o.getShort("month", month);
        day = o.getShort("day", day);
        week = o.getShort("week", week);
        weekDay = o.getShort("weekDay", weekDay);
        hour = o.getShort("hour", hour);
        minute = o.getShort("minute", minute);
        second = o.getShort("second", second);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        out.entry("year", year);
        out.entry("month", month);
        out.entry("day", day);
        out.entry("week", week);
        out.entry("weekday", weekDay);
        out.entry("hour", hour);
        out.entry("minute", minute);
        out.entry("second", second);
    }

}

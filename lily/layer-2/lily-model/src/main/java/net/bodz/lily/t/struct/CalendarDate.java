package net.bodz.lily.t.struct;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDateTime;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;

public class CalendarDate
        extends MixinStruct {

    short year = 0;
    short month = 0;
    short day = 0;
    short week = 0;
    short weekDay = 0;
    short hour = -1;
    short minute = -1;
    short second = -1;

    public CalendarDate() {
    }

    public CalendarDate(short year, short month, short day, short week, short weekDay, short hour, short minute,
            short second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.week = week;
        this.weekDay = weekDay;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

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
        String spec = o.getString("asString");
        if (spec != null) {
            setAsString(spec);
            return;
        }
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
        out.entry("asString", getAsString());
        out.entry("year", year);
        out.entry("month", month);
        out.entry("day", day);
        out.entry("week", week);
        out.entry("weekday", weekDay);
        out.entry("hour", hour);
        out.entry("minute", minute);
        out.entry("second", second);
    }

    public String getAsString() {
        StringBuilder sb = new StringBuilder(30);
        if (year != 0 || month != 0 || day != 0) {
            if (year > 0)
                sb.append(year);
            sb.append("-");
            if (month > 0)
                sb.append(month);
            sb.append("-");
            if (day > 0)
                sb.append(day);
        }
        if (week != 0 || weekDay != 0) {
            sb.append("w");
            if (week > 0)
                sb.append(week);
            sb.append("-");
            if (weekDay > 0)
                sb.append(weekDay);
        }
        if (hour != -1 || minute != -1 || second != -1) {
            sb.append(" ");
            if (hour >= 0)
                sb.append(hour);
            sb.append(":");
            if (minute >= 0)
                sb.append(minute);
            sb.append(":");
            if (second >= 0)
                sb.append(second);
        }
        return sb.toString();
    }

    public void setAsString(String spec)
            throws ParseException {
        spec = spec.trim();
        int sp = spec.indexOf(' ');
        String dateSpec, timeSpec;
        if (sp == -1) {
            dateSpec = spec.trim();
            timeSpec = null;
        } else {
            dateSpec = spec.substring(0, sp).trim();
            timeSpec = spec.substring(sp + 1).trim();
        }
        if (dateSpec.contains(":")) {
            String tmp = dateSpec;
            dateSpec = timeSpec;
            timeSpec = tmp;
        }

        if (!Nullables.isEmpty(dateSpec)) {
            parseDateSpec(dateSpec);
        } else {
            year = 0;
            month = 0;
            day = 0;
            week = 0;
            weekDay = 0;
        }

        if (!Nullables.isEmpty(timeSpec)) {
            parseTimeSpec(timeSpec);
        } else {
            hour = -1;
            minute = -1;
            second = -1;
        }
    }

    static Pattern dateSpecPattern = Pattern.compile("^(-?\\d*)-(-?\\d*)-(-?\\d*)(w(-?\\d*)-(-?\\d*))?$");
    static Pattern timeSpecPattern = Pattern.compile("^(-?\\d*):(-?\\d*)(:(-?\\d*))?$");

    void parseDateSpec(String spec)
            throws ParseException {
        Matcher matcher = dateSpecPattern.matcher(spec);
        if (matcher.matches()) {
            String _year = matcher.group(1);
            String _month = matcher.group(2);
            String _day = matcher.group(3);
            String _week = matcher.group(5);
            String _weekDay = matcher.group(6);
            year = _year.isEmpty() ? 0 : Short.parseShort(_year);
            month = _month.isEmpty() ? 0 : Short.parseShort(_month);
            day = _day.isEmpty() ? 0 : Short.parseShort(_day);
            if (_week != null) {
                week = _week.isEmpty() ? 0 : Short.parseShort(_week);
                weekDay = _weekDay.isEmpty() ? 0 : Short.parseShort(_weekDay);
            } else {
                week = 0;
                weekDay = 0;
            }
        } else {
            throw new ParseException("Illegal date spec: " + spec);
        }
    }

    void parseTimeSpec(String spec)
            throws ParseException {
        Matcher matcher = timeSpecPattern.matcher(spec);
        if (matcher.matches()) {
            String _hour = matcher.group(1);
            String _minute = matcher.group(2);
            String _second = matcher.group(4);
            hour = _hour.isEmpty() ? -1 : Short.parseShort(_hour);
            minute = _minute.isEmpty() ? -1 : Short.parseShort(_minute);
            if (_second != null)
                second = _second.isEmpty() ? -1 : Short.parseShort(_second);
            else
                second = -1;
        } else {
            throw new ParseException("Illegal time spec: " + spec);
        }
    }

    @Override
    public String toString() {
        return getAsString();
    }

}

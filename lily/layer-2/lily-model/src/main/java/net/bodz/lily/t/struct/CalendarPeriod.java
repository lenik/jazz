package net.bodz.lily.t.struct;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.Period;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;

public class CalendarPeriod
        extends MixinStruct {

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

    public String getAsString() {
        StringBuilder sb = new StringBuilder(30);
        if (years != 0 || months != 0 || days != 0) {
            sb.append(years);
            sb.append("-");
            sb.append(months);
            sb.append("-");
            sb.append(days);
        }
        if (weeks != 0) {
            sb.append("w");
            sb.append(weeks);
        }
        if (workDays) {
            sb.append("*");
        }

        if (hours != 0 || minutes != 0 || seconds != 0) {
            sb.append(" ");
            sb.append(hours);
            sb.append(":");
            sb.append(minutes);
            sb.append(":");
            sb.append(seconds);
        }
        return sb.toString();
    }

    public void setAsString(String spec)
            throws ParseException {
        spec = spec.trim();
        int sp = spec.indexOf(' ');
        String dateSpec, timeSpec;
        if (sp == -1) {
            dateSpec = spec;
            timeSpec = null;
        } else {
            dateSpec = spec.substring(0, sp);
            timeSpec = spec.substring(sp + 1);
        }
        parseDateSpec(dateSpec);
        parseTimeSpec(timeSpec);
    }

    static Pattern dateSpecPattern = Pattern.compile("^(\\d+)-(\\d+)-(\\d+)(w(\\d+))?(\\*?)$");
    static Pattern timeSpecPattern = Pattern.compile("^(\\d+):(\\d+):(\\d+)$");

    void parseDateSpec(String spec)
            throws ParseException {
        Matcher matcher = dateSpecPattern.matcher(spec);
        if (matcher.matches()) {
            String _years = matcher.group(1);
            String _months = matcher.group(2);
            String _days = matcher.group(3);
            String _weeks = matcher.group(5);
            String _workDay = matcher.group(6);
            years = Integer.parseInt(_years);
            months = Integer.parseInt(_months);
            days = Integer.parseInt(_days);
            weeks = _weeks == null ? 0 : Integer.parseInt(_weeks);
            workDays = _workDay.equals("*");
        } else {
            throw new ParseException("Illegal date spec: " + spec);
        }
    }

    void parseTimeSpec(String spec)
            throws ParseException {
        Matcher matcher = timeSpecPattern.matcher(spec);
        if (matcher.matches()) {
            String _hours = matcher.group(1);
            String _minutes = matcher.group(2);
            String _seconds = matcher.group(3);
            hours = Integer.parseInt(_hours);
            minutes = Integer.parseInt(_minutes);
            seconds = Integer.parseInt(_seconds);
        } else {
            throw new ParseException("Illegal time spec: " + spec);
        }
    }

    @Override
    public String toString() {
        return getAsString();
    }

}

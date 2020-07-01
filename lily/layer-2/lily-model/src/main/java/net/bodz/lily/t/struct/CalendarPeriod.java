package net.bodz.lily.t.struct;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.Period;

import net.bodz.bas.c.object.Nullables;
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
        String spec = o.getString("asString");
        if (spec != null) {
            setAsString(spec);
            return;
        }
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
        out.entry("asString", getAsString());
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
            if (years > 0)
                sb.append(years);
            sb.append("-");
            if (months > 0)
                sb.append(months);
            sb.append("-");
            if (days > 0)
                sb.append(days);
        }
        if (weeks > 0) {
            sb.append("w");
            sb.append(weeks);
        }
        if (workDays) {
            sb.append("*");
        }

        if (hours != 0 || minutes != 0 || seconds != 0) {
            sb.append(" ");
            if (hours > 0)
                sb.append(hours);
            sb.append(":");
            if (minutes > 0)
                sb.append(minutes);
            sb.append(":");
            if (seconds > 0)
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
            years = 0;
            months = 0;
            days = 0;
            weeks = 0;
            workDays = false;
        }

        if (!Nullables.isEmpty(timeSpec)) {
            parseTimeSpec(timeSpec);
        } else {
            hours = 0;
            minutes = 0;
            days = 0;
        }
    }

    static Pattern dateSpecPattern = Pattern.compile("^(-?\\d*)-(-?\\d*)-(-?\\d*)(w(-?\\d+))?(-?\\*?)$");
    static Pattern timeSpecPattern = Pattern.compile("^(-?\\d*):(-?\\d*)(:(-?\\d*))?$");

    void parseDateSpec(String spec)
            throws ParseException {
        Matcher matcher = dateSpecPattern.matcher(spec);
        if (matcher.matches()) {
            String _years = matcher.group(1);
            String _months = matcher.group(2);
            String _days = matcher.group(3);
            String _weeks = matcher.group(5);
            String _workDay = matcher.group(6);
            years = _years.isEmpty() ? 0 : Integer.parseInt(_years);
            months = _months.isEmpty() ? 0 : Integer.parseInt(_months);
            days = _days.isEmpty() ? 0 : Integer.parseInt(_days);
            if (_weeks != null)
                weeks = _weeks.isEmpty() ? 0 : Integer.parseInt(_weeks);
            else
                weeks = 0;
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
            String _seconds = matcher.group(4);
            hours = _hours.isEmpty() ? 0 : Integer.parseInt(_hours);
            minutes = _minutes.isEmpty() ? 0 : Integer.parseInt(_minutes);
            if (_seconds != null)
                seconds = _seconds.isEmpty() ? 0 : Integer.parseInt(_seconds);
            else
                seconds = 0;
        } else {
            throw new ParseException("Illegal time spec: " + spec);
        }
    }

    @Override
    public String toString() {
        return getAsString();
    }

}

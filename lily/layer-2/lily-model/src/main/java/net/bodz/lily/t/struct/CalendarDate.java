package net.bodz.lily.t.struct;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

/**
 * final class => final accessors => same as public fields.
 */
public final class CalendarDate
        extends MixinStruct
        implements IFieldVector {

    public static final int NO_YEAR = 0;
    public static final int NO_MONTH = 0;
    public static final int NO_DAY = 0;
    public static final int NO_WEEK = 0;
    public static final int NO_WEEKDAY = 0;
    public static final int NO_HOUR = -1;
    public static final int NO_MINUTE = -1;
    public static final int NO_SECOND = -1;

    static final Logger logger = LoggerFactory.getLogger(CalendarDate.class);

    short year = NO_YEAR;
    short month = NO_MONTH;
    short day = NO_DAY;
    short week = NO_WEEK;
    short weekDay = NO_WEEKDAY;
    short hour = NO_HOUR;
    short minute = NO_MINUTE;
    short second = NO_SECOND;
    DateTimeZone timeZone; // null for local-tz

    public CalendarDate() {
        this(DateTimeZone.getDefault());
    }

    public CalendarDate(DateTimeZone tz) {
        if (tz == null)
            throw new NullPointerException("tz");
        this.timeZone = tz;
    }

    public CalendarDate(short year, short month, short day, short week, short weekDay, short hour, short minute,
            short second) {
        this(DateTimeZone.getDefault(), year, month, day, week, weekDay, hour, minute, second);
    }

    public CalendarDate(DateTimeZone tz, short year, short month, short day, short week, short weekDay, short hour,
            short minute, short second) {
        this(tz);
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

    public DateTime toDateTime() {
        DateTime t = new DateTime(year, month, day, hour, minute, second, timeZone);
        return t;
    }

    public LocalDateTime toLocalDateTime() {
        LocalDateTime t = new LocalDateTime(year, month, day, hour, minute, second);
        return t;
    }

    public DateTime alignFrom(DateTime from) {
        TimeVec fromVec = new TimeVec(from);
        int[] point = fromVec.fields;

        int[] vals = toVector().fields;
        Field[] fields = new Field[8];
        int lastSpecField = -1;
        for (int i = 0; i < fields.length; i++) {
            int val = vals[i];
            if (val != -1) {
                fields[i] = new Field(val, val, val);
                lastSpecField = i;
                continue;
            }
            Field field = new Field(val, 0, 59);
            switch (i) {
            case FIELD_YEAR:
                field.setRange(point[FIELD_YEAR], 2100);
                break;
            case FIELD_MONTH:
                field.setRange(1, 12);
                break;
            case FIELD_DAY:
                field.setRange(1, 31);
                break;
            case FIELD_WEEK:
                field.setRange(1, 53);
                break;
            case FIELD_WEEKDAY:
                field.setRange(1, 7);
                break;
            case FIELD_HOUR:
                field.setRange(0, 23);
                break;
            }
            fields[i] = field;
        }

        for (int i = lastSpecField; i < fields.length; i++) {
            assert (fields[i].value == -1);
            fields[i].value = fields[i].min;
        }

        RangeResolver resolver = new RangeResolver(fields, fromVec.fields, lastSpecField);
        DateTime start = resolver.start(0, false);
        System.out.println("nconvert: " + resolver.nconvert);
        return start;
    }

    public DateTime alignFrom2(DateTime from) {
        TimeVec vector = new TimeVec(from);
        TimeVec pattern = toVector();
        vector.future(pattern.fields);
        return vector.toDateTime();
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
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
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
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
        if (year != NO_YEAR || month != NO_MONTH || day != NO_DAY) {
            if (year > NO_YEAR)
                sb.append(year);
            sb.append("-");
            if (month > NO_MONTH)
                sb.append(month);
            sb.append("-");
            if (day > NO_DAY)
                sb.append(day);
        }
        if (week != NO_WEEK || weekDay != NO_WEEKDAY) {
            sb.append("w");
            if (week != NO_WEEK)
                sb.append(week);
            sb.append("-");
            if (weekDay != NO_WEEKDAY)
                sb.append(weekDay);
        }
        if (hour != NO_HOUR || minute != NO_MINUTE || second != NO_SECOND) {
            sb.append(" ");
            if (hour != NO_HOUR)
                sb.append(hour);
            sb.append(":");
            if (minute != NO_MINUTE)
                sb.append(minute);
            sb.append(":");
            if (second != NO_SECOND)
                sb.append(second);
        }
        return sb.toString();
    }

    public void setAsString(String spec) {
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
            try {
                parseDateSpec(dateSpec);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Parse date: " + e.getMessage(), e);
            }
        } else {
            year = NO_YEAR;
            month = NO_MONTH;
            day = NO_DAY;
            week = NO_WEEK;
            weekDay = NO_WEEKDAY;
        }

        if (!Nullables.isEmpty(timeSpec)) {
            try {
                parseTimeSpec(timeSpec);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Parse time: " + e.getMessage(), e);
            }
        } else {
            hour = NO_HOUR;
            minute = NO_MINUTE;
            second = NO_SECOND;
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
            year = _year.isEmpty() ? NO_YEAR : Short.parseShort(_year);
            month = _month.isEmpty() ? NO_MONTH : Short.parseShort(_month);
            day = _day.isEmpty() ? NO_DAY : Short.parseShort(_day);
            if (_week != null) {
                week = _week.isEmpty() ? NO_WEEK : Short.parseShort(_week);
                weekDay = _weekDay.isEmpty() ? NO_WEEKDAY : Short.parseShort(_weekDay);
            } else {
                week = NO_WEEK;
                weekDay = NO_WEEKDAY;
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
            hour = _hour.isEmpty() ? NO_HOUR : Short.parseShort(_hour);
            minute = _minute.isEmpty() ? NO_MINUTE : Short.parseShort(_minute);
            if (_second != null)
                second = _second.isEmpty() ? NO_SECOND : Short.parseShort(_second);
            else
                second = NO_SECOND;
        } else {
            throw new ParseException("Illegal time spec: " + spec);
        }
    }

    @Override
    public String toString() {
        return getAsString();
    }

    public TimeVec toVector() {
        TimeVec vec = new TimeVec(timeZone);
        if (year != NO_YEAR)
            vec.fields[TimeVec.FIELD_YEAR] = year;
        if (month != NO_YEAR)
            vec.fields[TimeVec.FIELD_MONTH] = month;
        if (day != NO_YEAR)
            vec.fields[TimeVec.FIELD_DAY] = day;
        if (week != NO_YEAR)
            vec.fields[TimeVec.FIELD_WEEK] = week;
        if (weekDay != NO_YEAR)
            vec.fields[TimeVec.FIELD_WEEKDAY] = weekDay;
        if (hour != NO_YEAR)
            vec.fields[TimeVec.FIELD_HOUR] = hour;
        if (minute != NO_MINUTE)
            vec.fields[TimeVec.FIELD_MINUTE] = minute;
        if (second != NO_SECOND)
            vec.fields[TimeVec.FIELD_SECOND] = second;
        return vec;
    }

}

interface IFieldVector {

    static final int FIELD_YEAR = 0;
    static final int FIELD_MONTH = 1;
    static final int FIELD_DAY = 2;
    static final int FIELD_WEEK = 3;
    static final int FIELD_WEEKDAY = 4;
    static final int FIELD_HOUR = 5;
    static final int FIELD_MINUTE = 6;
    static final int FIELD_SECOND = 7;

}

class TimeVec
        implements IFieldVector {

    final int[] fields = new int[8];
    final DateTimeZone timeZone;

    // backward map
    static final int[] bmap = new int[] { -1, // year
            FIELD_YEAR, // month
            FIELD_WEEKDAY, // day
            FIELD_MONTH, // week
            FIELD_WEEK, // weekday
            FIELD_DAY, // hour
            FIELD_HOUR, // minute
            FIELD_MINUTE, // second
    };

    public TimeVec(DateTimeZone zone) {
        this.timeZone = zone;
        for (int i = 0; i < fields.length; i++)
            fields[i] = -1;
    }

    public TimeVec(DateTime dateTime) {
        this.timeZone = dateTime.getZone();
        setFields(dateTime);
    }

    public void setFields(DateTime dateTime) {
        fields[FIELD_YEAR] = dateTime.get(DateTimeFieldType.year());
        fields[FIELD_MONTH] = dateTime.get(DateTimeFieldType.monthOfYear());
        fields[FIELD_DAY] = dateTime.get(DateTimeFieldType.dayOfMonth());
        fields[FIELD_WEEK] = dateTime.get(DateTimeFieldType.weekOfWeekyear());
        fields[FIELD_WEEKDAY] = dateTime.get(DateTimeFieldType.dayOfWeek());
        fields[FIELD_HOUR] = dateTime.get(DateTimeFieldType.hourOfDay());
        fields[FIELD_MINUTE] = dateTime.get(DateTimeFieldType.minuteOfHour());
        fields[FIELD_SECOND] = dateTime.get(DateTimeFieldType.secondOfMinute());
    }

    public DateTime toDateTime() {
        DateTime dateTime = new DateTime(//
                fields[FIELD_YEAR], //
                fields[FIELD_MONTH], //
                fields[FIELD_DAY], //
                fields[FIELD_HOUR], //
                fields[FIELD_MINUTE], //
                fields[FIELD_SECOND], //
                timeZone);
        return dateTime;
    }

    void future(int[] pattern) {
        assert fields.length == pattern.length;
        int firstBeforeField = -1;
        for (int i = 0; i < fields.length; i++) {
            int p = pattern[i];
            if (p != -1) {
                if (p < fields[i]) { // is before than
                    firstBeforeField = i;
                }
            } else { // unspecified
                if (firstBeforeField != -1)
                    pattern[i] = 0;
            }
        }

        DateTime base = toDateTime();
        DateTime future = base;
        if (firstBeforeField != -1) { // any before-than field was found.
            switch (firstBeforeField) {
            case FIELD_YEAR:
                future = base.plusYears(1);
                break;
            case FIELD_MONTH:
                future = base.plusMonths(1);
                break;
            case FIELD_DAY:
            case FIELD_WEEKDAY:
                future = base.plusDays(1);
                break;
            case FIELD_HOUR:
                future = base.plusHours(1);
                break;
            case FIELD_MINUTE:
                future = base.plusMinutes(1);
                break;
            case FIELD_SECOND:
                future = base.plusSeconds(1);
                break;
            case FIELD_WEEK:
                future = base.plusWeeks(1);
                break;
            }
        }
        setFields(future);
    }

    @Override
    public String toString() {
        return StringArray.join(", ", fields);
    }

}

class Field {
    int value;
    int min;
    int max;
    Field prev;

    public Field(int value, int min, int max) {
        this(value, min, max, null);
    }

    public Field(int value, int min, int max, Field prev) {
        this.value = value;
        this.min = min;
        this.max = max;
        this.prev = prev;
    }

    int getMax() {
        return max;
    }

    public void setRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    boolean isFixed() {
        return value == min && min == max;
    }

    @Override
    public String toString() {
        return String.format("%s<%s..%s>", value, min, max);
    }

}

class RangeResolver
        implements IFieldVector {

    Field[] fields;
    int n;
    int[] point;
    DateTimeZone timeZone;
    int nconvert;

    public RangeResolver(Field[] fields, int[] point, int r) {
        this.fields = fields;
        this.n = r;
        this.point = point;
    }

    DateTime start(int i, boolean restart) {
        if (i >= n)
            return convert();

        Field field = fields[i];
        if (field.isFixed())
            return start(i + 1, restart);

        int start = restart ? field.min : point[i];
        int max = field.max;
        if (i == FIELD_DAY) {
            int year = fields[FIELD_YEAR].value;
            int month = fields[FIELD_MONTH].value;
            DateTime dt = new DateTime(year, month, 1, 0, 0, 0);
            dt = dt.plusMonths(1).minusDays(1);
            int monthSize = dt.getDayOfMonth();
            max = monthSize;
        }
        for (int val = start; val <= max; val++) {
            push(i, val);
            DateTime t = start(i + 1, restart || val > start);
            pop(i);
            if (t != null)
                return t;
        }
        return null;
    }

    Field[] push(int i, int val) {
        Field f = fields[i];
        fields[i] = new Field(val, f.min, f.max, f);
        return fields;
    }

    Field[] pop(int i) {
        fields[i] = fields[i].prev;
        return fields;
    }

    @Override
    public String toString() {
        short[] vec = new short[8];
        for (int i = 0; i < vec.length; i++)
            vec[i] = (short) fields[i].value;
        return new CalendarDate(//
                vec[0], vec[1], vec[2], vec[3], vec[4], vec[5], vec[6], vec[7]).toString();
    };

    DateTime convert() {
        System.out.println(" -- " + this);
        nconvert++;
        DateTime dateTime = new DateTime( //
                fields[FIELD_YEAR].value, //
                fields[FIELD_MONTH].value, //
                fields[FIELD_DAY].value, //
                fields[FIELD_HOUR].value, //
                fields[FIELD_MINUTE].value, //
                fields[FIELD_SECOND].value, //
                timeZone);

        int week = fields[FIELD_WEEK].value;
        int weekDay = fields[FIELD_WEEKDAY].value;
        if (week != -1) {
            if (week != dateTime.getWeekOfWeekyear())
                return null;
        }
        if (weekDay != -1) {
            if (weekDay != dateTime.getDayOfWeek())
                return null;
        }
        return dateTime;
    }
}

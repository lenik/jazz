package net.bodz.lily.t.struct;

import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.RangeVarsFn;
import net.bodz.bas.t.range.ShortRange;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * @see CalendarDate
 */
public class CalendarDateMask
        implements IVarMapForm {

    ShortRange yearRange = new ShortRange();
    ShortRange monthRange = new ShortRange();
    ShortRange dayRange = new ShortRange();
    ShortRange weekRange = new ShortRange();
    ShortRange weekDayRange = new ShortRange();
    ShortRange hourRange = new ShortRange();
    ShortRange minuteRange = new ShortRange();
    ShortRange secondRange = new ShortRange();

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        yearRange = RangeVarsFn.getFrom(map, "year", yearRange);
        monthRange = RangeVarsFn.getFrom(map, "month", monthRange);
        dayRange = RangeVarsFn.getFrom(map, "day", dayRange);
        weekRange = RangeVarsFn.getFrom(map, "week", weekRange);
        weekDayRange = RangeVarsFn.getFrom(map, "weekDay", weekDayRange);
        hourRange = RangeVarsFn.getFrom(map, "hour", hourRange);
        minuteRange = RangeVarsFn.getFrom(map, "minute", minuteRange);
        secondRange = RangeVarsFn.getFrom(map, "second", secondRange);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        map.put("yearRange", yearRange);
        map.put("monthRange", monthRange);
        map.put("dayRange", dayRange);
        map.put("weekRange", weekRange);
        map.put("weekDayRange", weekDayRange);
        map.put("hourRange", hourRange);
        map.put("minuteRange", minuteRange);
        map.put("secondRange", secondRange);
    }

    public String matchSql(String sqlPrefix) {
        StringBuilder sb = new StringBuilder(100);
        if (yearRange != null)
            sb.append(yearRange.matchSql(sqlPrefix + "year"));
        if (monthRange != null)
            sb.append(monthRange.matchSql(sqlPrefix + "month"));
        if (dayRange != null)
            sb.append(dayRange.matchSql(sqlPrefix + "day"));
        if (weekRange != null)
            sb.append(weekRange.matchSql(sqlPrefix + "week"));
        if (weekDayRange != null)
            sb.append(weekDayRange.matchSql(sqlPrefix + "weekDay"));
        if (hourRange != null)
            sb.append(hourRange.matchSql(sqlPrefix + "hour"));
        if (minuteRange != null)
            sb.append(minuteRange.matchSql(sqlPrefix + "minute"));
        if (secondRange != null)
            sb.append(secondRange.matchSql(sqlPrefix + "second"));
        return sb.toString();
    }

    public Short getYear() {
        return yearRange == null ? null : yearRange.getPointValue();
    }

    public void setYear(Short year) {
        this.yearRange = year == null ? null : new ShortRange().point(year);
    }

    public ShortRange getYearRange() {
        return yearRange;
    }

    public void setYearRange(ShortRange yearRange) {
        this.yearRange = yearRange;
    }

    public Short getMonth() {
        return monthRange == null ? null : monthRange.getPointValue();
    }

    public void setMonth(Short month) {
        this.monthRange = month == null ? null : new ShortRange().point(month);
    }

    public ShortRange getMonthRange() {
        return monthRange;
    }

    public void setMonthRange(ShortRange monthRange) {
        this.monthRange = monthRange;
    }

    public Short getDay() {
        return dayRange == null ? null : dayRange.getPointValue();
    }

    public void setDay(Short day) {
        this.dayRange = day == null ? null : new ShortRange().point(day);
    }

    public ShortRange getDayRange() {
        return dayRange;
    }

    public void setDayRange(ShortRange dayRange) {
        this.dayRange = dayRange;
    }

    public Short getWeek() {
        return weekRange == null ? null : weekRange.getPointValue();
    }

    public void setWeek(Short week) {
        this.weekRange = week == null ? null : new ShortRange().point(week);
    }

    public ShortRange getWeekRange() {
        return weekRange;
    }

    public void setWeekRange(ShortRange weekRange) {
        this.weekRange = weekRange;
    }

    public Short getWeekDay() {
        return weekDayRange == null ? null : weekDayRange.getPointValue();
    }

    public void setWeekDay(Short weekDay) {
        this.weekDayRange = weekDay == null ? null : new ShortRange().point(weekDay);
    }

    public ShortRange getWeekDayRange() {
        return weekDayRange;
    }

    public void setWeekDayRange(ShortRange weekDayRange) {
        this.weekDayRange = weekDayRange;
    }

    public Short getHour() {
        return hourRange == null ? null : hourRange.getPointValue();
    }

    public void setHour(Short hour) {
        this.hourRange = hour == null ? null : new ShortRange().point(hour);
    }

    public ShortRange getHourRange() {
        return hourRange;
    }

    public void setHourRange(ShortRange hourRange) {
        this.hourRange = hourRange;
    }

    public Short getMinute() {
        return minuteRange == null ? null : minuteRange.getPointValue();
    }

    public void setMinute(Short minute) {
        this.minuteRange = minute == null ? null : new ShortRange().point(minute);
    }

    public ShortRange getMinuteRange() {
        return minuteRange;
    }

    public void setMinuteRange(ShortRange minuteRange) {
        this.minuteRange = minuteRange;
    }

    public Short getSecond() {
        return secondRange == null ? null : secondRange.getPointValue();
    }

    public void setSecond(Short second) {
        this.secondRange = second == null ? null : new ShortRange().point(second);
    }

    public ShortRange getSecondRange() {
        return secondRange;
    }

    public void setSecondRange(ShortRange secondRange) {
        this.secondRange = secondRange;
    }

}

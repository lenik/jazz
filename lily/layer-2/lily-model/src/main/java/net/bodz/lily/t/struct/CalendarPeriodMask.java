package net.bodz.lily.t.struct;

import java.util.Map;

import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.RangeVarsFn;
import net.bodz.bas.t.variant.IVarMapSerializable;
import net.bodz.bas.t.variant.IVariantMap;

public class CalendarPeriodMask
        implements IVarMapSerializable {

    IntegerRange yearsRange;
    IntegerRange monthsRange;
    IntegerRange weeksRange;
    IntegerRange daysRange;
    Boolean workDays;
    IntegerRange hoursRange;
    IntegerRange minutesRange;
    IntegerRange secondsRange;

    @Override
    public void readObject(IVariantMap<String> map)
            throws LoaderException, ParseException {
        yearsRange = RangeVarsFn.getFrom(map, "years", yearsRange);
        monthsRange = RangeVarsFn.getFrom(map, "months", monthsRange);
        daysRange = RangeVarsFn.getFrom(map, "days", daysRange);
        weeksRange = RangeVarsFn.getFrom(map, "weeks", weeksRange);
        workDays = map.getBoolean("workDays", workDays);
        hoursRange = RangeVarsFn.getFrom(map, "hours", hoursRange);
        minutesRange = RangeVarsFn.getFrom(map, "minutes", minutesRange);
        secondsRange = RangeVarsFn.getFrom(map, "seconds", secondsRange);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
        map.put("yearsRange", yearsRange);
        map.put("monthsRange", monthsRange);
        map.put("daysRange", daysRange);
        map.put("weeksRange", weeksRange);
        map.put("workDays", workDays);
        map.put("hoursRange", hoursRange);
        map.put("minutesRange", minutesRange);
        map.put("secondsRange", secondsRange);
    }

    public String matchSql(String sqlPrefix) {
        StringBuilder sb = new StringBuilder(100);
        if (yearsRange != null)
            sb.append(yearsRange.matchSql(sqlPrefix + "years"));
        if (monthsRange != null)
            sb.append(monthsRange.matchSql(sqlPrefix + "months"));
        if (daysRange != null)
            sb.append(daysRange.matchSql(sqlPrefix + "days"));
        if (weeksRange != null)
            sb.append(weeksRange.matchSql(sqlPrefix + "weeks"));
        if (workDays != null)
            sb.append(sqlPrefix + "workDay=" + workDays);
        if (hoursRange != null)
            sb.append(hoursRange.matchSql(sqlPrefix + "hours"));
        if (minutesRange != null)
            sb.append(minutesRange.matchSql(sqlPrefix + "minutes"));
        if (secondsRange != null)
            sb.append(secondsRange.matchSql(sqlPrefix + "seconds"));
        return sb.toString();
    }

    public Integer getYears() {
        return yearsRange == null ? null : yearsRange.getPointValue();
    }

    public void setYears(Integer years) {
        this.yearsRange = years == null ? null : new IntegerRange().point(years);
    }

    public IntegerRange getYearsRange() {
        return yearsRange;
    }

    public void setYearsRange(IntegerRange yearsRange) {
        this.yearsRange = yearsRange;
    }

    public Integer getMonths() {
        return monthsRange == null ? null : monthsRange.getPointValue();
    }

    public void setMonths(Integer months) {
        this.monthsRange = months == null ? null : new IntegerRange().point(months);
    }

    public IntegerRange getMonthsRange() {
        return monthsRange;
    }

    public void setMonthsRange(IntegerRange monthsRange) {
        this.monthsRange = monthsRange;
    }

    public Integer getWeeks() {
        return weeksRange == null ? null : weeksRange.getPointValue();
    }

    public void setWeeks(Integer weeks) {
        this.weeksRange = weeks == null ? null : new IntegerRange().point(weeks);
    }

    public IntegerRange getWeeksRange() {
        return weeksRange;
    }

    public void setWeeksRange(IntegerRange weeksRange) {
        this.weeksRange = weeksRange;
    }

    public Integer getDays() {
        return daysRange == null ? null : daysRange.getPointValue();
    }

    public void setDays(Integer days) {
        this.daysRange = days == null ? null : new IntegerRange().point(days);
    }

    public IntegerRange getDaysRange() {
        return daysRange;
    }

    public void setDaysRange(IntegerRange daysRange) {
        this.daysRange = daysRange;
    }

    public Boolean getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Boolean workDays) {
        this.workDays = workDays;
    }

    public Integer getHours() {
        return hoursRange == null ? null : hoursRange.getPointValue();
    }

    public void setHours(Integer hours) {
        this.hoursRange = hours == null ? null : new IntegerRange().point(hours);
    }

    public IntegerRange getHoursRange() {
        return hoursRange;
    }

    public void setHoursRange(IntegerRange hoursRange) {
        this.hoursRange = hoursRange;
    }

    public Integer getMinutes() {
        return minutesRange == null ? null : minutesRange.getPointValue();
    }

    public void setMinutes(Integer minutes) {
        this.minutesRange = minutes == null ? null : new IntegerRange().point(minutes);
    }

    public IntegerRange getMinutesRange() {
        return minutesRange;
    }

    public void setMinutesRange(IntegerRange minutesRange) {
        this.minutesRange = minutesRange;
    }

    public Integer getSeconds() {
        return secondsRange == null ? null : secondsRange.getPointValue();
    }

    public void setSeconds(Integer seconds) {
        this.secondsRange = seconds == null ? null : new IntegerRange().point(seconds);
    }

    public IntegerRange getSecondsRange() {
        return secondsRange;
    }

    public void setSecondsRange(IntegerRange secondsRange) {
        this.secondsRange = secondsRange;
    }

}

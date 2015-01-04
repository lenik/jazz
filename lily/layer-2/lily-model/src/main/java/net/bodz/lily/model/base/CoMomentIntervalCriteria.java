package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;

import net.bodz.lily.model.sea.QVariantMap;

public class CoMomentIntervalCriteria
        extends CoEntityCriteria {

    public DateRange dateRange;
    public Integer year;

    public boolean noDate;
    public boolean noYear;

    /**
     * @label Date Range
     * @label.zh 时间范围
     */
    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    /**
     * @label Year
     * @label.zh 年份
     */
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isNoDate() {
        return noDate;
    }

    public void setNoDate(boolean noDate) {
        this.noDate = noDate;
    }

    public boolean isNoYear() {
        return noYear;
    }

    public void setNoYear(boolean noYear) {
        this.noYear = noYear;
    }

    @Override
    protected void populate(QVariantMap<String> map)
            throws ParseException {
        super.populate(map);
        dateRange = map.getDateRange("dates", dateRange);
        year = map.getInt("year", year);
        noDate = map.getBoolean("no-date");
        noYear = map.getBoolean("no-year");
    }

}

package net.bodz.lily.model.base;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.range.DateRange;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.bas.t.variant.QVariantMap;

public class CoMomentIntervalMask
        extends CoObjectMask {

    private DateRange dateRange;
    private Integer year; // = Calendar.getInstance().get(Calendar.YEAR);

    private boolean noDate;
    private boolean noYear;

    private Boolean started;
    private Boolean ended;

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

    public Boolean getStarted() {
        return started;
    }

    public void setStarted(Boolean started) {
        this.started = started;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    @Override
    public void readObject(IVariantMap<String> _map)
            throws ParseException {
        super.readObject(_map);
        QVariantMap<String> map = QVariantMap.from(_map);
        dateRange = map.getDateRange("dates", dateRange);
        year = map.getInt("year", year);
        noDate = map.getBoolean("no-date");
        noYear = map.getBoolean("no-year");
        started = map.getBoolean("started", started);
        ended = map.getBoolean("ended", ended);
    }

}

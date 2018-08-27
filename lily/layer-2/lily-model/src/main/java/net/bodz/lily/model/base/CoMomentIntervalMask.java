package net.bodz.lily.model.base;

import net.bodz.bas.t.range.DateTimeRange;

public class CoMomentIntervalMask
        extends CoObjectMask {

    private DateTimeRange beginTime = new DateTimeRange();
    private DateTimeRange endTime = new DateTimeRange();
    private Integer year; // = Calendar.getInstance().get(Calendar.YEAR);

    private boolean noDate;
    private boolean noYear;

    private Boolean started;
    private Boolean ended;

    /**
     * @label Date Range
     * @label.zh 时间范围
     */
    public DateTimeRange getDate() {
        return beginTime;
    }

    public void setDate(DateTimeRange date) {
        this.beginTime = date;
    }

    public DateTimeRange getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(DateTimeRange beginTime) {
        this.beginTime = beginTime;
    }

    public DateTimeRange getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTimeRange endTime) {
        this.endTime = endTime;
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

}

package net.bodz.lily.concrete;

import java.time.ZonedDateTime;

import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.meta.CriteriaClass;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.ID_TYPE })
@CriteriaClass(CoMomentIntervalCriteriaBuilder.class)
@IncludeMapperXml
public abstract class CoMomentInterval<Id>
        extends IdEntity<Id>
        implements
            IMomentInterval {

    private static final long serialVersionUID = 1L;

    private ZonedDateTime beginTime;
    private ZonedDateTime endTime;
    private int year;

    public CoMomentInterval() {
        beginTime = getCreationDate();
    }

    /**
     * <p lang="zh">
     * 事件的开始时间（具体的发生时间，或期待的发生时间）。
     * <p>
     * 当未指定时，表示尚未开始或开始于无限远以前。
     *
     * @label Begin Date
     * @label.zh 开始时间
     */
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public ZonedDateTime getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(ZonedDateTime beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * <p lang="zh">
     * 事件的结束时间（具体的完成时间，或期待的完成时间）。
     * <p>
     * 当未指定时，表示尚未结束或结束于无限远以后。
     *
     * @label End Date
     * @label.zh 结束时间
     */
    @OfGroup(StdGroup.Schedule.class)
    @Override
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @see #getBeginTime()
     */
    @Deprecated
    @Derived
    @OfGroup(StdGroup.Schedule.class)
    public final ZonedDateTime getBeginDate() {
        return getBeginTime();
    }

    /**
     * @see #setBeginTime(ZonedDateTime)
     */
    @Deprecated
    public final void setBeginDate(ZonedDateTime beginDate) {
        setBeginTime(beginDate);
    }

    /**
     * @see #getEndTime()
     */
    @Deprecated
    @Derived
    @OfGroup(StdGroup.Schedule.class)
    public final ZonedDateTime getEndDate() {
        return getEndTime();
    }

    /**
     * @see #setEndTime(ZonedDateTime)
     */
    @Deprecated
    public final void setEndDate(ZonedDateTime endDate) {
        setEndTime(endDate);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        beginTime = o.getZonedDateTime("beginTime", beginTime);
        endTime = o.getZonedDateTime("endTime", endTime);
    }

}

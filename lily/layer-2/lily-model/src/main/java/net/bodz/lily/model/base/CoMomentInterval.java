package net.bodz.lily.model.base;

import java.io.IOException;

import org.joda.time.DateTime;

import net.bodz.bas.db.ibatis.IncludeMapperXml;
import net.bodz.bas.html.io.IHtmlOut;
import net.bodz.bas.html.viz.IHtmlViewContext;
import net.bodz.bas.meta.cache.Derived;
import net.bodz.bas.repr.form.meta.FormInput;
import net.bodz.bas.repr.form.meta.OfGroup;
import net.bodz.bas.repr.form.meta.StdGroup;
import net.bodz.lily.entity.IId;
import net.bodz.lily.entity.IMomentInterval;

@IncludeMapperXml
public abstract class CoMomentInterval<Id>
        extends CoObject
        implements IMomentInterval, IId<Id> {

    private static final long serialVersionUID = 1L;

    private Id id;
    private DateTime beginTime;
    private DateTime endTime;

    public CoMomentInterval() {
        beginTime = getCreationDate();
    }

    @Override
    public Class<Id> idType() {
        return IId.fn._getIdType(getClass());
    }

    @FormInput(readOnly = true)
    @Override
    public Id getId() {
        return id;
    }

    @Override
    public void setId(Id id) {
        this.id = id;
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
    public DateTime getBeginDate() {
        return beginDate;
    }

    @Override
    public void setBeginDate(DateTime beginTime) {
        this.beginDate = beginTime;
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
    public DateTime getEndDate() {
        return endDate;
    }

    @Override
    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * @see #getBeginTime()
     */
    @Deprecated
    @Derived
    @OfGroup(StdGroup.Schedule.class)
    public final DateTime getBeginDate() {
        return getBeginTime();
    }

    /**
     * @see #setBeginTime(DateTime)
     */
    public final void setBeginDate(DateTime beginDate) {
        setBeginTime(beginDate);
    }

    /**
     * @see #getEndTime()
     */
    @Derived
    @OfGroup(StdGroup.Schedule.class)
    public final DateTime getEndDate() {
        return getEndTime();
    }

    /**
     * @see #setEndTime(DateTime)
     */
    @Deprecated
    public final void setEndDate(DateTime endDate) {
        setEndTime(endDate);
    }

    @Override
    public Id persist(IHtmlViewContext ctx, IHtmlOut out)
            throws IOException {
        Id id = (Id) super.persist(ctx, out);
        setId(id);
        return id;
    }

}

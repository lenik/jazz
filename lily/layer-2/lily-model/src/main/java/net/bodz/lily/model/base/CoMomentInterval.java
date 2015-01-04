package net.bodz.lily.model.base;

import java.util.Date;

import net.bodz.bas.repr.form.meta.OfGroup;

public abstract class CoMomentInterval
        extends CoEntity
        implements IMomentInterval {

    private static final long serialVersionUID = 1L;

    private Date beginDate;
    private Date endDate;

    public abstract Object getId();

    /**
     * @label Begin Date
     * @label.zh 开始时间
     */
    @OfGroup(IMomentInterval.class)
    @Override
    public Date getBeginDate() {
        return beginDate;
    }

    @Override
    public void setBeginDate(Date beginTime) {
        this.beginDate = beginTime;
    }

    /**
     * @label End Date
     * @label.zh 结束时间
     */
    @OfGroup(IMomentInterval.class)
    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endTime) {
        this.endDate = endTime;
    }

}

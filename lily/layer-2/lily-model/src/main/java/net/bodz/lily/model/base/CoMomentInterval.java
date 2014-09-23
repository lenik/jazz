package net.bodz.lily.model.base;

import java.util.Date;

public abstract class CoMomentInterval
        extends CoEntity
        implements IMomentInterval {

    private static final long serialVersionUID = 1L;

    private Date beginTime;
    private Date endTime;

    @Override
    public Date getBeginTime() {
        return beginTime;
    }

    @Override
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @Override
    public Date getEndTime() {
        return endTime;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}

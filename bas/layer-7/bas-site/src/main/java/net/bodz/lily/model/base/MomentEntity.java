package net.bodz.lily.model.base;

import java.util.Date;

public abstract class MomentEntity
        extends CoEntity
        implements IMomentInterval {

    private static final long serialVersionUID = 1L;

    private Date time;

    @Override
    public Date getBeginTime() {
        return time;
    }

    @Override
    public void setBeginTime(Date beginTime) {
        this.time = beginTime;
    }

    @Override
    public Date getEndTime() {
        return time;
    }

    @Override
    public void setEndTime(Date endTime) {
        this.time = endTime;
    }

}

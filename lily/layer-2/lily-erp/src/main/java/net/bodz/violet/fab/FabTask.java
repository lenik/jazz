package net.bodz.violet.fab;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;

@Table(name = "fabtask")
@IdType(Long.class)
public class FabTask
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    FabOrder order;
    DateTime since;
    DateTime deadline;

    Integer lockCount;
    Integer processCount;
    Integer trackCount;

    public FabTask() {
    }

    public FabOrder getOrder() {
        return order;
    }

    public void setOrder(FabOrder order) {
        this.order = order;
    }

    public DateTime getSince() {
        return since;
    }

    public void setSince(DateTime since) {
        this.since = since;
    }

    public DateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    /**
     * storelock: create locks for fabproc* should be finer.
     */
    public Integer getLockCount() {
        return lockCount;
    }

    public void setLockCount(Integer lockCount) {
        this.lockCount = lockCount;
    }

    public Integer getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Integer processCount) {
        this.processCount = processCount;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

}

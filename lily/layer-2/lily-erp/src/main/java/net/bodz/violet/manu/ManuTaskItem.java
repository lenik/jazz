package net.bodz.violet.manu;

import java.math.BigDecimal;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.art.ArtifactModel;

@Table(name = "manutaskl")
@IdType(Long.class)
public class ManuTaskItem
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    ManuTask task;
    DateTime deadline;
    String status;

    ArtifactModel model;
    BigDecimal quantity = BigDecimal.ONE;

    Integer trackCount;

    public ManuTaskItem() {
    }

    public ManuTask getTask() {
        return task;
    }

    public void setTask(ManuTask task) {
        this.task = task;
    }

    public DateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArtifactModel getModel() {
        return model;
    }

    public void setModel(ArtifactModel model) {
        this.model = model;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

}

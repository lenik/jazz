package net.bodz.violet.manu;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.art.ArtifactModel;

@IdType(Long.class)
public class ManuProcess
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    ManuTask task;
    ManuProcess parent;
    ArtifactModel output;
    ManuStdProcess standard;

    BigDecimal quantity = BigDecimal.ZERO;

    DateTime since;
    DateTime deadline;
    Integer trackCount;

    public ManuProcess() {
    }

    public ManuTask getTask() {
        return task;
    }

    public void setTask(ManuTask task) {
        this.task = task;
    }

    /**
     * so forms the full process tree.
     */
    public ManuProcess getParent() {
        return parent;
    }

    public void setParent(ManuProcess parent) {
        this.parent = parent;
    }

    /**
     * (intermediate) output, maybe different to the task.
     */
    public ArtifactModel getOutput() {
        return output;
    }

    public void setOutput(ArtifactModel output) {
        this.output = output;
    }

    public ManuStdProcess getStandard() {
        return standard;
    }

    public void setStandard(ManuStdProcess standard) {
        this.standard = standard;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * Scheduled start time
     */
    public DateTime getSince() {
        return since;
    }

    public void setSince(DateTime since) {
        this.since = since;
    }

    /**
     * Scheduled end time
     */
    public DateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }

    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.trackCount = trackCount;
    }

}

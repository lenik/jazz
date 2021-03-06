package net.bodz.violet.fab;

import java.math.BigDecimal;

import org.joda.time.DateTime;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;
import net.bodz.violet.art.ArtifactModel;

@IdType(Long.class)
public class FabProcess
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    FabTask task;
    FabProcess parent;
    ArtifactModel output;
    FabStdProcess standard;

    BigDecimal quantity = BigDecimal.ZERO;

    DateTime since;
    DateTime deadline;
    Integer trackCount;

    public FabProcess() {
    }

    public FabTask getTask() {
        return task;
    }

    public void setTask(FabTask task) {
        this.task = task;
    }

    /**
     * so forms the full process tree.
     */
    public FabProcess getParent() {
        return parent;
    }

    public void setParent(FabProcess parent) {
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

    public FabStdProcess getStandard() {
        return standard;
    }

    public void setStandard(FabStdProcess standard) {
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

package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMomentInterval;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.ArtifactModel;

@IdType(Long.class)
public abstract class _FabProcess_stuff
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabproc";

    public static final String FIELD_TASK_ID = "task";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_OUTPUT_ID = "output";
    public static final String FIELD_STANDARD_ID = "std";
    public static final String FIELD_QUANTITY = "qty";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_SINCE = "since";
    public static final String FIELD_DEADLINE = "deadline";
    public static final String FIELD_TRACK_COUNT = "ntrack";

    public static final int N_TASK_ID = 19;
    public static final int N_PARENT_ID = 19;
    public static final int N_OUTPUT_ID = 10;
    public static final int N_STANDARD_ID = 10;
    public static final int N_QUANTITY = 20;
    public static final int N_BATCH = 2147483647;
    public static final int N_SINCE = 35;
    public static final int N_DEADLINE = 35;
    public static final int N_TRACK_COUNT = 10;

    private static final int _ord_TASK_ID = 17;
    private static final int _ord_PARENT_ID = _ord_TASK_ID + 1;
    private static final int _ord_OUTPUT_ID = _ord_PARENT_ID + 1;
    private static final int _ord_STANDARD_ID = _ord_OUTPUT_ID + 1;
    private static final int _ord_QUANTITY = _ord_STANDARD_ID + 1;
    private static final int _ord_BATCH = _ord_QUANTITY + 1;
    private static final int _ord_SINCE = _ord_BATCH + 1;
    private static final int _ord_DEADLINE = _ord_SINCE + 1;
    private static final int _ord_TRACK_COUNT = _ord_DEADLINE + 1;

    @NotNull
    BigDecimal quantity;

    Object batch;

    @NotNull
    Timestamp since;

    @NotNull
    Timestamp deadline;

    Integer trackCount;

    /**  */
    @NotNull
    FabStdProcess standard;

    @NotNull
    int standardId;

    /**  */
    FabProcess parent;

    Long parentId;

    /**  */
    @NotNull
    ArtifactModel output;

    @NotNull
    int outputId;

    /**  */
    @NotNull
    FabTask task;

    @NotNull
    long taskId;

    @Ordinal(_ord_QUANTITY)
    @NotNull
    @Precision(value = N_QUANTITY, scale = 2)
    @Column(name = "qty", nullable = false, precision = 20, scale = 2)
    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull BigDecimal value) {
        this.quantity = value;
    }

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
        this.batch = value;
    }

    @Ordinal(_ord_SINCE)
    @NotNull
    @Precision(value = 35, scale = 6)
    @Column(name = "since", nullable = false, precision = 35, scale = 6)
    public Timestamp getSince() {
        return since;
    }

    public void setSince(@NotNull Timestamp value) {
        this.since = value;
    }

    @Ordinal(_ord_DEADLINE)
    @NotNull
    @Precision(value = 35, scale = 6)
    @Column(name = "deadline", nullable = false, precision = 35, scale = 6)
    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(@NotNull Timestamp value) {
        this.deadline = value;
    }

    @Ordinal(_ord_TRACK_COUNT)
    @Precision(value = N_TRACK_COUNT)
    @Column(name = "ntrack", precision = 10)
    public Integer getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(Integer value) {
        this.trackCount = value;
    }

    /**
     *
     * @label std
     * @constraint foreign key (std) references violet.fabstdproc (id)
     */
    @NotNull
    public FabStdProcess getStandard() {
        return standard;
    }

    /**
     */
    public void setStandard(@NotNull FabStdProcess value) {
        this.standard = value;
    }

    @Ordinal(_ord_STANDARD_ID)
    @Precision(value = 10)
    @Column(name = "std", nullable = false, precision = 10)
    public synchronized int getStandardId() {
        if (standard != null) {
            return standard.getId();
        }
        return standardId;
    }

    public synchronized void setStandardId(int value) {
        this.standardId = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references violet.fabproc (id)
     */
    public FabProcess getParent() {
        return parent;
    }

    /**
     */
    public void setParent(FabProcess value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 19)
    public synchronized Long getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Long value) {
        this.parentId = value;
    }

    /**
     *
     * @label output
     * @constraint foreign key (output) references violet.artmodel (id)
     */
    @NotNull
    public ArtifactModel getOutput() {
        return output;
    }

    /**
     */
    public void setOutput(@NotNull ArtifactModel value) {
        this.output = value;
    }

    @Ordinal(_ord_OUTPUT_ID)
    @Precision(value = 10)
    @Column(name = "output", nullable = false, precision = 10)
    public synchronized int getOutputId() {
        if (output != null) {
            return output.getId();
        }
        return outputId;
    }

    public synchronized void setOutputId(int value) {
        this.outputId = value;
    }

    /**
     *
     * @label task
     * @constraint foreign key (task) references violet.fabtask (id)
     */
    @NotNull
    public FabTask getTask() {
        return task;
    }

    /**
     */
    public void setTask(@NotNull FabTask value) {
        this.task = value;
    }

    @Ordinal(_ord_TASK_ID)
    @Precision(value = 19)
    @Column(name = "task", nullable = false, precision = 19)
    public synchronized long getTaskId() {
        if (task != null) {
            return task.getId();
        }
        return taskId;
    }

    public synchronized void setTaskId(long value) {
        this.taskId = value;
    }

    public void initNotNulls() {
        this.quantity = BigDecimal.ZERO;
        this.since = new Timestamp(System.currentTimeMillis());
        this.deadline = new Timestamp(System.currentTimeMillis());
    }

}

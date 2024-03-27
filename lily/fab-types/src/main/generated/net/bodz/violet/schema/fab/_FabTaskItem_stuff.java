package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEvent;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.ArtifactModel;

@IdType(Long.class)
public abstract class _FabTaskItem_stuff
        extends CoEvent<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtaskl";

    public static final String FIELD_TASK_ID = "task";
    public static final String FIELD_DEADLINE = "deadline";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_MODEL_ID = "model";
    public static final String FIELD_QUANTITY = "qty";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_TRACK_COUNT = "ntrack";

    public static final int N_TASK_ID = 19;
    public static final int N_DEADLINE = 35;
    public static final int N_STATUS = 100;
    public static final int N_MODEL_ID = 10;
    public static final int N_QUANTITY = 20;
    public static final int N_BATCH = 2147483647;
    public static final int N_TRACK_COUNT = 10;

    private static final int _ord_TASK_ID = 11;
    private static final int _ord_DEADLINE = _ord_TASK_ID + 1;
    private static final int _ord_STATUS = _ord_DEADLINE + 1;
    private static final int _ord_MODEL_ID = _ord_STATUS + 1;
    private static final int _ord_QUANTITY = _ord_MODEL_ID + 1;
    private static final int _ord_BATCH = _ord_QUANTITY + 1;
    private static final int _ord_TRACK_COUNT = _ord_BATCH + 1;

    @NotNull
    OffsetDateTime deadline;

    String status;

    @NotNull
    BigDecimal quantity;

    JsonVariant batch;

    Integer trackCount;

    /**  */
    @NotNull
    FabTask task;

    @NotNull
    long taskId;

    /**  */
    @NotNull
    ArtifactModel model;

    @NotNull
    int modelId;

    @Ordinal(_ord_DEADLINE)
    @NotNull
    @Precision(value = 35, scale = 6)
    @Column(name = "deadline", nullable = false, precision = 35, scale = 6)
    public OffsetDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(@NotNull OffsetDateTime value) {
        this.deadline = value;
    }

    @Ordinal(_ord_STATUS)
    @Precision(value = N_STATUS)
    @TextInput(maxLength = N_STATUS)
    @Column(name = "status", length = N_STATUS)
    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

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
    public JsonVariant getBatch() {
        return batch;
    }

    public void setBatch(JsonVariant value) {
        this.batch = value;
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
     * @constraint foreign key (task) references violet.fabtask (id)
     */
    @JoinColumn(name = "task")
    @ManyToOne
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
            if (task.getId() == null)
                return 0L;
            return task.getId();
        }
        return taskId;
    }

    public synchronized void setTaskId(long value) {
        this.taskId = value;
    }

    /**
     *
     * @constraint foreign key (model) references violet.artmodel (id)
     */
    @JoinColumn(name = "model")
    @ManyToOne
    @NotNull
    public ArtifactModel getModel() {
        return model;
    }

    /**
     */
    public void setModel(@NotNull ArtifactModel value) {
        this.model = value;
    }

    @Ordinal(_ord_MODEL_ID)
    @Precision(value = 10)
    @Column(name = "model", nullable = false, precision = 10)
    public synchronized int getModelId() {
        if (model != null) {
            if (model.getId() == null)
                return 0;
            return model.getId();
        }
        return modelId;
    }

    public synchronized void setModelId(int value) {
        this.modelId = value;
    }

    public void initNotNulls() {
        this.deadline = OffsetDateTime.now();
        this.quantity = BigDecimal.ZERO;
    }

}

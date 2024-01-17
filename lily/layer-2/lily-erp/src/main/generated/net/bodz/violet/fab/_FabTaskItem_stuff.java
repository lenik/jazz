package net.bodz.violet.fab;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import org.joda.time.DateTime;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;
import net.bodz.violet.art.ArtifactModel;

@IdType(Long.class)
public abstract class _FabTaskItem_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtaskl";

    public static final String FIELD_ID = "id";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_TASK_ID = "task";
    public static final String FIELD_DEADLINE = "deadline";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_MODEL_ID = "model";
    public static final String FIELD_QUANTITY = "qty";
    public static final String FIELD_BATCH = "batch";
    public static final String FIELD_TRACK_COUNT = "ntrack";

    public static final int N_ID = 19;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_TASK_ID = 19;
    public static final int N_DEADLINE = 35;
    public static final int N_STATUS = 100;
    public static final int N_MODEL_ID = 10;
    public static final int N_QUANTITY = 20;
    public static final int N_BATCH = 2147483647;
    public static final int N_TRACK_COUNT = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = _ord_ID + 7;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_TASK_ID = _ord_YEAR + 1;
    private static final int _ord_DEADLINE = _ord_TASK_ID + 1;
    private static final int _ord_STATUS = _ord_DEADLINE + 1;
    private static final int _ord_MODEL_ID = _ord_STATUS + 1;
    private static final int _ord_QUANTITY = _ord_MODEL_ID + 1;
    private static final int _ord_BATCH = _ord_QUANTITY + 1;
    private static final int _ord_TRACK_COUNT = _ord_BATCH + 1;

    @Id
    @NotNull
    long id;

    DateTime beginTime;

    DateTime endTime;

    @NotNull
    int year;

    @NotNull
    Timestamp deadline;

    String status;

    @NotNull
    BigDecimal quantity;

    Object batch;

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

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public DateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(DateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
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
    public Object getBatch() {
        return batch;
    }

    public void setBatch(Object value) {
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

    /**
     *
     * @label model
     * @constraint foreign key (model) references violet.artmodel (id)
     */
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
        this.deadline = new Timestamp(System.currentTimeMillis());
        this.quantity = BigDecimal.ZERO;
    }

}

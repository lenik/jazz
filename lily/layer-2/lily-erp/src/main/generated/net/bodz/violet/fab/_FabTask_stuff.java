package net.bodz.violet.fab;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Id;

import org.joda.time.DateTime;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _FabTask_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtask";

    public static final String FIELD_ID = "id";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_ORDER_ID = "odr";
    public static final String FIELD_SINCE = "since";
    public static final String FIELD_DEADLINE = "deadline";
    public static final String FIELD_PROCESS_COUNT = "nproc";
    public static final String FIELD_TRACK_COUNT = "ntrack";

    public static final int N_ID = 19;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_ORDER_ID = 19;
    public static final int N_SINCE = 35;
    public static final int N_DEADLINE = 35;
    public static final int N_PROCESS_COUNT = 10;
    public static final int N_TRACK_COUNT = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_BEGIN_TIME = 14;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_ORDER_ID = _ord_YEAR + 1;
    private static final int _ord_SINCE = _ord_ORDER_ID + 1;
    private static final int _ord_DEADLINE = _ord_SINCE + 1;
    private static final int _ord_PROCESS_COUNT = _ord_DEADLINE + 1;
    private static final int _ord_TRACK_COUNT = _ord_PROCESS_COUNT + 1;

    @Id
    @NotNull
    long id;

    DateTime beginTime;

    DateTime endTime;

    @NotNull
    int year;

    @NotNull
    Timestamp since;

    @NotNull
    Timestamp deadline;

    Integer processCount;

    Integer trackCount;

    /**  */
    @NotNull
    FabOrder order;

    @NotNull
    long orderId;

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

    @Ordinal(_ord_PROCESS_COUNT)
    @Precision(value = N_PROCESS_COUNT)
    @Column(name = "nproc", precision = 10)
    public Integer getProcessCount() {
        return processCount;
    }

    public void setProcessCount(Integer value) {
        this.processCount = value;
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
     * @label odr
     * @constraint foreign key (odr) references violet.fabodr (id)
     */
    @NotNull
    public FabOrder getOrder() {
        return order;
    }

    /**
     */
    public void setOrder(@NotNull FabOrder value) {
        this.order = value;
    }

    @Ordinal(_ord_ORDER_ID)
    @Precision(value = 19)
    @Column(name = "odr", nullable = false, precision = 19)
    public synchronized long getOrderId() {
        if (order != null) {
            if (order.getId() == null)
                return 0L;
            return order.getId();
        }
        return orderId;
    }

    public synchronized void setOrderId(long value) {
        this.orderId = value;
    }

    public void initNotNulls() {
        this.since = new Timestamp(System.currentTimeMillis());
        this.deadline = new Timestamp(System.currentTimeMillis());
    }

}

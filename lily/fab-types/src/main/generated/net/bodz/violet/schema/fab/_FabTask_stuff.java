package net.bodz.violet.schema.fab;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEvent;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _FabTask_stuff
        extends CoEvent<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtask";

    public static final String FIELD_ORDER_ID = "odr";
    public static final String FIELD_SINCE = "since";
    public static final String FIELD_DEADLINE = "deadline";
    public static final String FIELD_PROCESS_COUNT = "nproc";
    public static final String FIELD_TRACK_COUNT = "ntrack";

    public static final int N_ORDER_ID = 19;
    public static final int N_SINCE = 35;
    public static final int N_DEADLINE = 35;
    public static final int N_PROCESS_COUNT = 10;
    public static final int N_TRACK_COUNT = 10;

    private static final int _ord_ORDER_ID = 18;
    private static final int _ord_SINCE = _ord_ORDER_ID + 1;
    private static final int _ord_DEADLINE = _ord_SINCE + 1;
    private static final int _ord_PROCESS_COUNT = _ord_DEADLINE + 1;
    private static final int _ord_TRACK_COUNT = _ord_PROCESS_COUNT + 1;

    @NotNull
    OffsetDateTime since;

    @NotNull
    OffsetDateTime deadline;

    Integer processCount;

    Integer trackCount;

    /**  */
    @NotNull
    FabOrder order;

    @NotNull
    long orderId;

    @Ordinal(_ord_SINCE)
    @NotNull
    @Precision(value = 35, scale = 6)
    @Column(name = "since", nullable = false, precision = 35, scale = 6)
    public OffsetDateTime getSince() {
        return since;
    }

    public void setSince(@NotNull OffsetDateTime value) {
        this.since = value;
    }

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
     * @constraint foreign key (odr) references violet.fabodr (id)
     */
    @JoinColumn(name = "odr")
    @ManyToOne
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
        this.since = OffsetDateTime.now();
        this.deadline = OffsetDateTime.now();
    }

}

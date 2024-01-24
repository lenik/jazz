package net.bodz.violet.schema.fab;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMomentInterval;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.contact.OrgUnit;

@IdType(Long.class)
public abstract class _FabTrack_stuff
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtrack";

    public static final String FIELD_PROCESS_ID = "proc";
    public static final String FIELD_SINCE = "since";
    public static final String FIELD_DEADLINE = "deadline";
    public static final String FIELD_PLANNED_QUANTITY = "qty_plan";
    public static final String FIELD_ACTUAL_QUANTITY = "qty_actual";
    public static final String FIELD_VALID_QUANTITY = "qty_valid";
    public static final String FIELD_ORG_UNIT_ID = "ou";

    public static final int N_PROCESS_ID = 19;
    public static final int N_SINCE = 35;
    public static final int N_DEADLINE = 35;
    public static final int N_PLANNED_QUANTITY = 20;
    public static final int N_ACTUAL_QUANTITY = 20;
    public static final int N_VALID_QUANTITY = 20;
    public static final int N_ORG_UNIT_ID = 10;

    private static final int _ord_PROCESS_ID = 17;
    private static final int _ord_SINCE = _ord_PROCESS_ID + 1;
    private static final int _ord_DEADLINE = _ord_SINCE + 1;
    private static final int _ord_PLANNED_QUANTITY = _ord_DEADLINE + 1;
    private static final int _ord_ACTUAL_QUANTITY = _ord_PLANNED_QUANTITY + 1;
    private static final int _ord_VALID_QUANTITY = _ord_ACTUAL_QUANTITY + 1;
    private static final int _ord_ORG_UNIT_ID = _ord_VALID_QUANTITY + 1;

    @NotNull
    Timestamp since;

    @NotNull
    Timestamp deadline;

    @NotNull
    BigDecimal plannedQuantity;

    @NotNull
    BigDecimal actualQuantity;

    @NotNull
    BigDecimal validQuantity;

    /**  */
    OrgUnit orgUnit;

    Integer orgUnitId;

    /**  */
    @NotNull
    FabProcess process;

    @NotNull
    long processId;

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

    @Ordinal(_ord_PLANNED_QUANTITY)
    @NotNull
    @Precision(value = N_PLANNED_QUANTITY, scale = 2)
    @Column(name = "qty_plan", nullable = false, precision = 20, scale = 2)
    public BigDecimal getPlannedQuantity() {
        return plannedQuantity;
    }

    public void setPlannedQuantity(@NotNull BigDecimal value) {
        this.plannedQuantity = value;
    }

    @Ordinal(_ord_ACTUAL_QUANTITY)
    @NotNull
    @Precision(value = N_ACTUAL_QUANTITY, scale = 2)
    @Column(name = "qty_actual", nullable = false, precision = 20, scale = 2)
    public BigDecimal getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(@NotNull BigDecimal value) {
        this.actualQuantity = value;
    }

    @Ordinal(_ord_VALID_QUANTITY)
    @NotNull
    @Precision(value = N_VALID_QUANTITY, scale = 2)
    @Column(name = "qty_valid", nullable = false, precision = 20, scale = 2)
    public BigDecimal getValidQuantity() {
        return validQuantity;
    }

    public void setValidQuantity(@NotNull BigDecimal value) {
        this.validQuantity = value;
    }

    /**
     *
     * @label ou
     * @constraint foreign key (ou) references lily.orgunit (id)
     */
    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    /**
     */
    public void setOrgUnit(OrgUnit value) {
        this.orgUnit = value;
    }

    @Ordinal(_ord_ORG_UNIT_ID)
    @Precision(value = N_ORG_UNIT_ID)
    @Column(name = "ou", precision = 10)
    public synchronized Integer getOrgUnitId() {
        if (orgUnit != null) {
            return orgUnit.getId();
        }
        return orgUnitId;
    }

    public synchronized void setOrgUnitId(Integer value) {
        this.orgUnitId = value;
    }

    /**
     *
     * @label proc
     * @constraint foreign key (proc) references violet.fabproc (id)
     */
    @NotNull
    public FabProcess getProcess() {
        return process;
    }

    /**
     */
    public void setProcess(@NotNull FabProcess value) {
        this.process = value;
    }

    @Ordinal(_ord_PROCESS_ID)
    @Precision(value = 19)
    @Column(name = "proc", nullable = false, precision = 19)
    public synchronized long getProcessId() {
        if (process != null) {
            return process.getId();
        }
        return processId;
    }

    public synchronized void setProcessId(long value) {
        this.processId = value;
    }

    public void initNotNulls() {
        this.since = new Timestamp(System.currentTimeMillis());
        this.deadline = new Timestamp(System.currentTimeMillis());
        this.plannedQuantity = BigDecimal.ZERO;
        this.actualQuantity = BigDecimal.ZERO;
        this.validQuantity = BigDecimal.ZERO;
    }

}

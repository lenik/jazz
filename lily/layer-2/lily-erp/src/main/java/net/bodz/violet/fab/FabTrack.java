package net.bodz.violet.fab;

import java.math.BigDecimal;

import javax.persistence.Table;

import org.joda.time.DateTime;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;

@Table(name = "fabtrack")
@IdType(Long.class)
public class FabTrack
        extends CoMomentInterval<Long> {

    private static final long serialVersionUID = 1L;

    FabProcess process;
    DateTime since;
    DateTime deadline;

    BigDecimal plannedQuantity = BigDecimal.ZERO;
    BigDecimal actualQuantity = BigDecimal.ZERO;
    BigDecimal validQuantity = BigDecimal.ZERO;

    OrgUnit orgUnit;

    public FabTrack() {
    }

    public FabProcess getProcess() {
        return process;
    }

    public void setProcess(FabProcess process) {
        this.process = process;
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

    public BigDecimal getPlannedQuantity() {
        return plannedQuantity;
    }

    public void setPlannedQuantity(BigDecimal plannedQuantity) {
        this.plannedQuantity = plannedQuantity;
    }

    public BigDecimal getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(BigDecimal actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public BigDecimal getValidQuantity() {
        return validQuantity;
    }

    public void setValidQuantity(BigDecimal validQuantity) {
        this.validQuantity = validQuantity;
    }

    public OrgUnit getOrgUnit() {
        return orgUnit;
    }

    public void setOrgUnit(OrgUnit orgUnit) {
        this.orgUnit = orgUnit;
    }

}

package net.bodz.violet.plan.dao;

import net.bodz.bas.t.range.IntegerRange;
import net.bodz.bas.t.range.LongRange;
import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PlanPartyCriteriaBuilder_stuff
        extends CoObjectCriteriaBuilder {

    Long id;
    LongRange idRange = new LongRange();

    Long planId;
    LongRange planIdRange = new LongRange();

    Integer personId;
    IntegerRange personIdRange = new IntegerRange();

    Integer orgId;
    IntegerRange orgIdRange = new IntegerRange();

    public Long getId() {
        return id;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public LongRange getIdRange() {
        return idRange;
    }

    public void setIdRange(LongRange range) {
        this.idRange = range;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long value) {
        this.planId = value;
    }

    public LongRange getPlanIdRange() {
        return planIdRange;
    }

    public void setPlanIdRange(LongRange range) {
        this.planIdRange = range;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer value) {
        this.personId = value;
    }

    public IntegerRange getPersonIdRange() {
        return personIdRange;
    }

    public void setPersonIdRange(IntegerRange range) {
        this.personIdRange = range;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer value) {
        this.orgId = value;
    }

    public IntegerRange getOrgIdRange() {
        return orgIdRange;
    }

    public void setOrgIdRange(IntegerRange range) {
        this.orgIdRange = range;
    }

}

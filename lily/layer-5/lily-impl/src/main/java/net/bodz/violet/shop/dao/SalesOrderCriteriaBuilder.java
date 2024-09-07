package net.bodz.violet.shop.dao;

import net.bodz.bas.t.range.DoubleRange;
import net.bodz.lily.t.base.CoMessageCriteriaBuilder;

public class SalesOrderCriteriaBuilder
        extends CoMessageCriteriaBuilder {

    Long planId;
    Integer orgId;
    Integer personId;

    DoubleRange quantityRange;
    DoubleRange totalRange;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public DoubleRange getQuantityRange() {
        return quantityRange;
    }

    public void setQuantityRange(DoubleRange quantityRange) {
        this.quantityRange = quantityRange;
    }

    public DoubleRange getTotalRange() {
        return totalRange;
    }

    public void setTotalRange(DoubleRange totalRange) {
        this.totalRange = totalRange;
    }

}

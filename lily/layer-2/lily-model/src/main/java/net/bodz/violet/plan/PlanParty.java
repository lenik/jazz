package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "plan_party")
@IdType(Integer.class)
public class PlanParty
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public PlanParty() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planParty: ...");
        return sb.toString();
    }

}

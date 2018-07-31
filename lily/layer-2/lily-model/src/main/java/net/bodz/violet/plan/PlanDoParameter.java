package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "plandoparm")
@IdType(Integer.class)
public class PlanDoParameter
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public PlanDoParameter() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLogParameter: ...");
        return sb.toString();
    }

}

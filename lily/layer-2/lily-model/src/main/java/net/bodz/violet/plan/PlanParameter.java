package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoParameter;

@Table(name = "planparm")
@IdType(Integer.class)
public class PlanParameter
        extends CoParameter<PlanParameter> {

    private static final long serialVersionUID = 1L;

    public PlanParameter() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planParameter: ...");
        return sb.toString();
    }

}

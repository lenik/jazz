package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.CoParameter;

@Table(name = "plandoparm")
public class PlanDoParameter
        extends CoParameter<PlanDoParameter> {

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

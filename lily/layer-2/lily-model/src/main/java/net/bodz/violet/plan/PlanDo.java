package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoMomentInterval;

@Table(name = "plando")
@IdType(Integer.class)
public class PlanDo
        extends CoMomentInterval<Integer> {

    private static final long serialVersionUID = 1L;

    public PlanDo() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("planLog: ...");
        return sb.toString();
    }

}

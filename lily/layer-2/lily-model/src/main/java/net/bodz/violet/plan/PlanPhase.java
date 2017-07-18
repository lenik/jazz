package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoNode;

@IdType(Integer.class)
@Table(name = "planphase")
public class PlanPhase
        extends CoNode<PlanPhase, Integer> {

    private static final long serialVersionUID = 1L;

    public PlanPhase() {
        super();
    }

    public PlanPhase(PlanPhase parent) {
        super(parent);
    }

}

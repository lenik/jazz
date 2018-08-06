package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.CoPhase;

@Table(name = "planphase")
public class PlanPhase
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public PlanPhase() {
        super();
    }

    public PlanPhase(PlanPhase parent) {
        super(parent);
    }

}

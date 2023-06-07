package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.PlanPhase;

/**
* @label PlanPhase
*/
@ObjectType(PlanPhase.class)
public class PlanPhaseIndex
        extends CoIndex<PlanPhase, PlanPhaseMask> {

    public PlanPhaseIndex() {
    }

}

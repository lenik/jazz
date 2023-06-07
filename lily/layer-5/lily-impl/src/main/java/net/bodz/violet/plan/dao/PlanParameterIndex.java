package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.PlanParameter;

/**
* @label PlanParameter
*/
@ObjectType(PlanParameter.class)
public class PlanParameterIndex
        extends CoIndex<PlanParameter, PlanParameterMask> {

    public PlanParameterIndex() {
    }

}

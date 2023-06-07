package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.Plan;

/**
* @label Plan
*/
@ObjectType(Plan.class)
public class PlanIndex
        extends CoIndex<Plan, PlanMask> {

    public PlanIndex() {
    }

}

package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.PlanParty;

/**
* @label PlanParty
*/
@ObjectType(PlanParty.class)
public class PlanPartyIndex
        extends CoIndex<PlanParty, PlanPartyCriteriaBuilder> {

    public PlanPartyIndex() {
    }

}

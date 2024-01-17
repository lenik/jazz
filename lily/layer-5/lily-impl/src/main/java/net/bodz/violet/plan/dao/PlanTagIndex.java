package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.PlanTag;

/**
* @label PlanTag
*/
@ObjectType(PlanTag.class)
public class PlanTagIndex
        extends CoIndex<PlanTag, PlanTagCriteriaBuilder> {

    public PlanTagIndex() {
    }

}

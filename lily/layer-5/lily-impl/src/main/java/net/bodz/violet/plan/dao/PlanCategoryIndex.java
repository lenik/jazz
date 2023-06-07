package net.bodz.violet.plan.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.plan.PlanCategory;

/**
* @label PlanCategory
*/
@ObjectType(PlanCategory.class)
public class PlanCategoryIndex
        extends CoIndex<PlanCategory, PlanCategoryMask> {

    public PlanCategoryIndex() {
    }

}
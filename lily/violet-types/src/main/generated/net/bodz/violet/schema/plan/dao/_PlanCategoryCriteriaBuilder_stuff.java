package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.PlanCategory;

@ForEntityType(PlanCategory.class)
public class _PlanCategoryCriteriaBuilder_stuff<self_t extends _PlanCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField name = string("\"name\"");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}

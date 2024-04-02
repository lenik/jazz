package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.DiaryPhase;

@ForEntityType(DiaryPhase.class)
public class _DiaryPhaseCriteriaBuilder_stuff<self_t extends _DiaryPhaseCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}

package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.DiaryTag;

@ForEntityType(DiaryTag.class)
public class _DiaryTagCriteriaBuilder_stuff<self_t extends _DiaryTagCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}

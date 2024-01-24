package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;

public class _DiaryParameterCriteriaBuilder_stuff<self_t extends _DiaryParameterCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField dummy = integer("dummy");

}

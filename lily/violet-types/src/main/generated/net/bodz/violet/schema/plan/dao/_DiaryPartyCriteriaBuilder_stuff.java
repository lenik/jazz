package net.bodz.violet.schema.plan.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.plan.DiaryParty;

@ForEntityType(DiaryParty.class)
public class _DiaryPartyCriteriaBuilder_stuff<self_t extends _DiaryPartyCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField diaryId = _long("diary");

    public final IntegerField userId = integer("\"user\"");

    public final IntegerField personId = integer("person");

    public final IntegerField orgId = integer("org");

    public final IntegerField value = integer("\"value\"");

}

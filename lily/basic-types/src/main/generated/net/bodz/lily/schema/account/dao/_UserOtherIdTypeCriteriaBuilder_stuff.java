package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.account.UserOtherIdType;

@ForEntityType(UserOtherIdType.class)
public class _UserOtherIdTypeCriteriaBuilder_stuff<self_t extends _UserOtherIdTypeCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField dummy = integer("dummy");

}

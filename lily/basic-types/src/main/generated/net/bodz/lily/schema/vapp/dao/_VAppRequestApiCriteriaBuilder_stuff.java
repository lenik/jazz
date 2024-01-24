package net.bodz.lily.schema.vapp.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _VAppRequestApiCriteriaBuilder_stuff<self_t extends _VAppRequestApiCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField parentId = integer("parent");

    public final IntegerField apiId = integer("api");

}

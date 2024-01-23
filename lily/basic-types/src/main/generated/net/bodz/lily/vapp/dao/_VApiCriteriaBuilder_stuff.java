package net.bodz.lily.vapp.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _VApiCriteriaBuilder_stuff<self_t extends _VApiCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField appId = integer("app");

    public final IntegerField apiId = integer("api");

    public final StringField callback = string("callback");

}

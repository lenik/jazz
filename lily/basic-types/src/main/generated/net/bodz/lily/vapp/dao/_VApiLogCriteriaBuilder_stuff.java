package net.bodz.lily.vapp.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _VApiLogCriteriaBuilder_stuff<self_t extends _VApiLogCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField appId = integer("app");

    public final IntegerField apiId = integer("api");

    public final StringField message = string("message");

    public final StringField err = string("err");

}

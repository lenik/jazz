package net.bodz.lily.vapp.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _VAppCriteriaBuilder_stuff<self_t extends _VAppCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField code = string("code");

    public final IntegerField reqId = integer("req");

    public final IntegerField categoryId = integer("cat");

    public final StringField secret = string("secret");

}

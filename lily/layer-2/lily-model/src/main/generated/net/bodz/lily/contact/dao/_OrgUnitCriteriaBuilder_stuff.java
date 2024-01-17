package net.bodz.lily.contact.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _OrgUnitCriteriaBuilder_stuff<self_t extends _OrgUnitCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField orgId = integer("org");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

}

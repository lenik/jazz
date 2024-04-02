package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.contact.OrgUnit;

@ForEntityType(OrgUnit.class)
public class _OrgUnitCriteriaBuilder_stuff<self_t extends _OrgUnitCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField orgId = integer("org");

    public final IntegerField parentId = integer("parent");

    public final IntegerField depth = integer("\"depth\"");

}

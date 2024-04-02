package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.contact.PersonRole;

@ForEntityType(PersonRole.class)
public class _PersonRoleCriteriaBuilder_stuff<self_t extends _PersonRoleCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField orgId = integer("org");

    public final IntegerField orgUnitId = integer("ou");

    public final IntegerField personId = integer("person");

    public final StringField role = string("\"role\"");

}

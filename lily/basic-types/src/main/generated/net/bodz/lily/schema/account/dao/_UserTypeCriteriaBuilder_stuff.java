package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _UserTypeCriteriaBuilder_stuff<self_t extends _UserTypeCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** The user type name */
    public final StringField name = string("\"name\"");

    public final IntegerField dummy = integer("dummy");

}

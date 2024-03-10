package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;

public class _GroupTypeCriteriaBuilder_stuff<self_t extends _GroupTypeCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** Group type name (unique) */
    public final StringField name = string("\"name\"");

    public final IntegerField dummy = integer("dummy");

}

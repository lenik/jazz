package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _FormParameterCriteriaBuilder_stuff<self_t extends _FormParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField formId = integer("form");

    public final StringField value = string("\"value\"");

}

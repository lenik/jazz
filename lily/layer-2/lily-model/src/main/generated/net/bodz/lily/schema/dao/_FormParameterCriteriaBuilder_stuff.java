package net.bodz.lily.schema.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _FormParameterCriteriaBuilder_stuff<self_t extends _FormParameterCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField formId = integer("form");

    public final StringField value = string("\"value\"");

}

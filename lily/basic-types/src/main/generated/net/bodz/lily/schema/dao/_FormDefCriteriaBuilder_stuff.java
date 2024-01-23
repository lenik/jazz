package net.bodz.lily.schema.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _FormDefCriteriaBuilder_stuff<self_t extends _FormDefCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField code = string("code");

    public final IntegerField schemaId = integer("\"schema\"");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

}

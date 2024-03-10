package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;

public class _FormDefCriteriaBuilder_stuff<self_t extends _FormDefCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField schemaId = integer("\"schema\"");

    public final StringField subject = string("subject");

    public final StringField rawText = string("text");

}

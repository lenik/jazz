package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;

public class _TagGroupDefCriteriaBuilder_stuff<self_t extends _TagGroupDefCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField schemaId = integer("\"schema\"");

    public final BooleanField forTopic = bool("topic");

    public final BooleanField forReply = bool("reply");

}

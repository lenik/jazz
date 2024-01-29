package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.concrete.CoCategoryCriteriaBuilder;

public class _PartyCategoryCriteriaBuilder_stuff<self_t extends _PartyCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final StringField name = string("\"name\"");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField image = string("image");

    public final StringField imageAlt = string("imagealt");

    public final IntegerField refCount = integer("nref");

}

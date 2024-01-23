package net.bodz.violet.edu.dao;

import net.bodz.lily.template.CoCategoryCriteriaBuilder;

public class _CourseCategoryCriteriaBuilder_stuff<self_t extends _CourseCategoryCriteriaBuilder_stuff<self_t>>
        extends CoCategoryCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField image = string("image");

    public final StringField imageAlt = string("imagealt");

    public final IntegerField refCount = integer("nref");

}

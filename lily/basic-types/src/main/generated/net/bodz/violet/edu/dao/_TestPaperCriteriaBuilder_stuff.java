package net.bodz.violet.edu.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _TestPaperCriteriaBuilder_stuff<self_t extends _TestPaperCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField courseId = integer("course");

    public final IntegerField timeout = integer("timeout");

    public final IntegerField totalscore = integer("totalscore");

}

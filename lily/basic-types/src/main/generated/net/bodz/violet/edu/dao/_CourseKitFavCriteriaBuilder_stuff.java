package net.bodz.violet.edu.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _CourseKitFavCriteriaBuilder_stuff<self_t extends _CourseKitFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField coursekitId = integer("coursekit");

    public final IntegerField userId = integer("\"user\"");

}

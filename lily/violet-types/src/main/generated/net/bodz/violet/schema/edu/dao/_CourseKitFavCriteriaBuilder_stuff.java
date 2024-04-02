package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.CourseKitFav;

@ForEntityType(CourseKitFav.class)
public class _CourseKitFavCriteriaBuilder_stuff<self_t extends _CourseKitFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final IntegerField coursekitId = integer("coursekit");

    public final IntegerField userId = integer("\"user\"");

}

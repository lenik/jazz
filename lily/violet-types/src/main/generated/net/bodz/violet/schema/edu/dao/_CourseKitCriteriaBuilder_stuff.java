package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.CourseKit;

@ForEntityType(CourseKit.class)
public class _CourseKitCriteriaBuilder_stuff<self_t extends _CourseKitCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField categoryId = integer("cat");

    public final IntegerField courseId = integer("course");

    public final DiscreteField<JsonVariant> files = discrete("files", JsonVariant.class);

    public final IntegerField favCount = integer("nfav");

    public final IntegerField voteCount = integer("nvote");

    public final IntegerField hateCount = integer("nhate");

    public final DiscreteField<JsonVariant> dummy = discrete("dummy", JsonVariant.class);

}

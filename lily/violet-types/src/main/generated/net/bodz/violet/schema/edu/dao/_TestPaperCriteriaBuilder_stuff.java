package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoMessageCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestPaper;

@ForEntityType(TestPaper.class)
public class _TestPaperCriteriaBuilder_stuff<self_t extends _TestPaperCriteriaBuilder_stuff<self_t>>
        extends CoMessageCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField courseId = integer("course");

    public final IntegerField timeout = integer("timeout");

    public final IntegerField totalscore = integer("totalscore");

}

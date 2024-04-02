package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.edu.TestApplyItem;

@ForEntityType(TestApplyItem.class)
public class _TestApplyItemCriteriaBuilder_stuff<self_t extends _TestApplyItemCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField applyId = _long("apply");

    public final LongField questionId = _long("q");

    public final IntegerField answer = integer("ans");

    public final StringField anstext = string("anstext");

    public final DoubleField score = _double("score");

    public final DoubleField waittime = _double("waittime");

}

package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.issue.IssueParameter;

@ForEntityType(IssueParameter.class)
public class _IssueParameterCriteriaBuilder_stuff<self_t extends _IssueParameterCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField name = string("\"name\"");

    public final IntegerField dummy = integer("dummy");

}

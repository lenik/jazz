package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.IssueFav;

/**
* @label IssueFav
*/
@ObjectType(IssueFav.class)
public class IssueFavIndex
        extends CoIndex<IssueFav, IssueFavCriteriaBuilder> {

    public IssueFavIndex() {
    }

}

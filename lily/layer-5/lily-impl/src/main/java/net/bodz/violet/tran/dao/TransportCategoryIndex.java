package net.bodz.violet.tran.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.tran.TransportCategory;

/**
* @label TransportCategory
*/
@ObjectType(TransportCategory.class)
public class TransportCategoryIndex
        extends CoIndex<TransportCategory, TransportCategoryCriteriaBuilder> {

    public TransportCategoryIndex() {
    }

}

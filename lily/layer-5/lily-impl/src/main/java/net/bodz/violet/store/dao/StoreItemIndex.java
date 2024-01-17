package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.StoreItem;

/**
* @label StoreItem
*/
@ObjectType(StoreItem.class)
public class StoreItemIndex
        extends CoIndex<StoreItem, StoreItemCriteriaBuilder> {

    public StoreItemIndex() {
    }

}

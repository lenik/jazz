package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.StoreOrderItem;

/**
* @label StoreOrderItem
*/
@ObjectType(StoreOrderItem.class)
public class StoreOrderItemIndex
        extends CoIndex<StoreOrderItem, StoreOrderItemCriteriaBuilder> {

    public StoreOrderItemIndex() {
    }

}

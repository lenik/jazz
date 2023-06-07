package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.StoreOrder;

/**
* @label StoreOrder
*/
@ObjectType(StoreOrder.class)
public class StoreOrderIndex
        extends CoIndex<StoreOrder, StoreOrderMask> {

    public StoreOrderIndex() {
    }

}

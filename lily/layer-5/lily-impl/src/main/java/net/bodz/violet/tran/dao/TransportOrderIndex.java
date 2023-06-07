package net.bodz.violet.tran.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.tran.TransportOrder;

/**
 * 送货单
 *
 * @label 送货单
 */
@ObjectType(TransportOrder.class)
public class TransportOrderIndex
        extends CoIndex<TransportOrder, TransportOrderMask> {

    public TransportOrderIndex() {
    }

}

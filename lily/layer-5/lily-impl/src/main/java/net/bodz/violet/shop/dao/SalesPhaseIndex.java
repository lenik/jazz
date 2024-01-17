package net.bodz.violet.shop.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.shop.SalesPhase;

/**
* @label SalesPhase
*/
@ObjectType(SalesPhase.class)
public class SalesPhaseIndex
        extends CoIndex<SalesPhase, SalesPhaseCriteriaBuilder> {

    public SalesPhaseIndex() {
    }

}

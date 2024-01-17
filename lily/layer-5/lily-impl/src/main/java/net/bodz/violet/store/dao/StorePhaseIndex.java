package net.bodz.violet.store.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.store.StorePhase;

/**
* @label StorePhase
*/
@ObjectType(StorePhase.class)
public class StorePhaseIndex
        extends CoIndex<StorePhase, StorePhaseCriteriaBuilder> {

    public StorePhaseIndex() {
    }

}

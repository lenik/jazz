package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoCode;

@IdType(Integer.class)
@Table(name = "storephase")
public class StorePhase
        extends CoCode<StorePhase> {

    private static final long serialVersionUID = 1L;

    public StorePhase() {
        super();
    }

    public StorePhase(StorePhase parent) {
        super(parent);
    }

}

package net.bodz.violet.store;

import javax.persistence.Table;

import net.bodz.lily.template.CoPhase;

@Table(name = "storephase")
public class StorePhase
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public StorePhase() {
        super();
    }

    public StorePhase(StorePhase parent) {
        super(parent);
    }

}
